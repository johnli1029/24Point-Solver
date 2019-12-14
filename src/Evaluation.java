public class Evaluation {
  public static boolean test(Expression exp, int expectedVal, int... substitutions) {
    try {
      return Math.abs(exp.evaluate(substitutions) - expectedVal) / expectedVal < 1e-5;
    } catch (ArithmeticException ignored) {
      return false;
    }
  }

  public static void main(String[] args) {
    int[] substitutions = {32, 14, 18, 12, 2, 3, 5};
    int expectedVal = 24;

    new FullEnumerator().getAllExpressions(Var.getVarList(substitutions.length)).stream()
        .filter(expression -> test(expression, expectedVal, substitutions))
        .forEach(expression -> System.out.println(expression.toString(substitutions) + " = " + expectedVal));
  }
}
