class ChargeTransaction {
  private Integer id;
  private Integer userId;
  private Double price;
  private Double amountKWh;
  private Boolean payed;

  private constructor(Integer id, Integer userId, Double price, Double amountKWh, Boolean payed) {
    this.id = id;
    this.userId = userId;
    this.price = price;
    this.amountKWh = amountKWh;
    this.payed = payed;
  }

  public static ChargeTransaction from(ResultSet result) {
    return new ChargeTransaction(
      rs.getInt("id"),
      rs.getInt("user_id"),
      rs.getDouble("price"),
      rs.getDouble("amount_kwh"),
      rs.getBoolean("payed")
    );
  }

  public Integer getId() {
    return id;
  }

  public Integer getUserId() {
    return userId;
  }

  public Double getPrice() {
    return price;
  }

  public Double getAmountKWh() {
    return amountKWh;
  }

  public Boolean isPayed() {
    return payed;
  }

  public setUserId(Integer userId) {
    this.userId = userId;
  }

  public setPrice(Double price) {
    this.price = price;
  }

  public setAmountKWh(Double amountKWh) {
    this.amountKWh = amountKWh;
  }

  public setPayed(Boolean payed) {
    this.payed = payed;
  }
}