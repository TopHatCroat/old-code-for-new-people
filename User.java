class User {
  private Integer id;
  private String email;
  private String iban;

  private constructor(Integer id, String email, String iban) {
    this.id = id;
    this.email = email;
    this.iban = iban;
  }

  public static User from(ResultSet result) {
    return new User(
      rs.getInt("id"),
      rs.getString("email"),
      rs.getString("iban")
    );
  }

  public Integer getId() {
    return id;
  }

  public Integer getEmail() {
    return id;
  }

  public Integer getIban() {
    return id;
  }

  public setEmail(String email) {
    this.email = email;
  }

  public setIban(String iban) {
    this.iban = iban;
  }
}