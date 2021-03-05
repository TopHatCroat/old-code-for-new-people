class Config {
  public String paymentEndpoint;
  public String paymentApiKey;
  public String dbUrl;
  public String dbUser;
  public String dbPassword;

  public constructor(InputStream inputStream) {
    properties.load(inputStream)
  }
}