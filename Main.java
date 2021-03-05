import java.io.InputStream;

class Main {
  public static void main(String[] args) {
    InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties");
    Config config = new Config(inputStream);
    Example example = new Example(config);

    Map<String, Double> results = example.getResult(args[0]);
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      String key = entry.getKey();
      Double value = entry.getValue();
      System.out.println(key + " = " + value.toString());
    }
  }
}