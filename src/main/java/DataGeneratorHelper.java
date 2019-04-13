import java.util.Date;

public class DataGeneratorHelper {
  public static String generateNewName() {
    return String.format("Test %s", new Date().getTime());
  }
}
