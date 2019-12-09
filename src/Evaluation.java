import java.util.Arrays;

public class Evaluation {
  public static boolean test(Expression exp, int a, int b, int c, int d, int expected) {
    try {
      return Math.abs(exp.evaluate(3, 3, 8, 8) - 24) / 24 < 1e-5;
    } catch (ArithmeticException ignored) {
      return false;
    }
  }

  public static void main(String[] args) {
    new Enumerator().getAllExpressions(Arrays.asList(
        new Var("A"), new Var("B"), new Var("C"), new Var("D"))).stream()
        .filter(expression -> test(expression, 3, 3, 8, 8, 24))
        .forEach(expression -> System.out.println(expression + " = 24"));

  }
}
