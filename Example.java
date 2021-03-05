package de.emonvia.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Example {

    private final BankApi bankApi;

    private final Connection db;

    public Example(Config config) throws SQLException {
        this.bankApi = new BankApi(config.paymentEndpoint, config.paymentApiKey);
        this.db = DriverManager.getConnection(config.dbUrl, config.dbUser, config.dbPassword);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Double> getResult(@RequestParam("parameter") String parameter) {
        Map<String, Double> result = new HashMap<>();
        List<User> users = new LinkedList<>();

        try {
            ResultSet usersSet = this.db.createStatement().executeQuery("SELECT * FROM users WHERE email LIKE '%" + parameter + "%'");
            while (usersSet.next()) {
                users.add(User.from(usersSet));
            }

            for (User user : users) {
                List<ChargeTransaction> chargeTransactions = new LinkedList<>();

                ResultSet transactionsSet = this.db.createStatement().executeQuery(
                  "SELECT * FROM charge_transaction WHERE user_id = " + user.getId()
                );
                while (transactionsSet.next()) {
                    chargeTransactions.add(ChargeTransaction.from(transactionsSet));
                }

                Double total = 0d;
                for (ChargeTransaction ct : chargeTransactions) {
                    if (ct.getPrice().compareTo(0d) == 1) {
                        total += ct.getPrice();
                    }
                }

                try {
                    bankApi.executePayment(user.getIban(), total);
                    result.put(user.getEmail(), total);
                } catch (BankApiException e) {
                    System.out.println("Error processing payment");
                    return null;
                }
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Processing completed at: " + Instant.now() + "\n Results: " + result.size());
        return result;
    }
}
