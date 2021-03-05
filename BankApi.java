class BankApi {
  private String endpoint;
  private String apiKey;

  constructor(String endpoint, String apiKey) {
    this.endpoint = endpoint;
    this.apiKey = apiKey;
  }

  public boolean executePayment(String iban, Double amount) {
    // some REST API call to payment provider
    return true
  }
}