import java.math.BigInteger;

public class Utils {
  private Utils() {
  }

  private static BigInteger recFac(int start, int n) {
    if (n <= 16) {
      BigInteger result = new BigInteger(String.valueOf(start));
      for (int i = start + 1; i < start + n; i++) result = result.multiply(new BigInteger(String.valueOf(i)));
      return result;
    }
    int i = n / 2;
    return recFac(start, i).multiply(recFac(start + i, n - i));
  }

  public static BigInteger factorial(int n) {
    return recFac(1, n);
  }

  public static void main(String[] args) {
    System.out.println(recFac(1, 10));
    System.out.println(recFac(11, 10));

    System.out.println(factorial(200));
  }

}
