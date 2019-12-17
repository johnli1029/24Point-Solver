import java.util.Set;
import java.util.stream.Collectors;

/**
 * Case Testing Program
 */
public class Evaluation {
  /**
   * Return whether the Expression {@code exp} can be evaluated to result {@code expectedVal}
   * by the substitution {@code substitutions}
   *
   * @param exp           the expression to be evaluated, containing variables
   * @param expectedVal   the expected evaluation value
   * @param substitutions the evaluation mapping between variables and values
   */
  public static boolean test(Expression exp, int expectedVal, int... substitutions) {
    try {
      return Math.abs(exp.evaluate(substitutions) - expectedVal) / expectedVal < 1e-5;
    } catch (ArithmeticException ignored) {
      return false;
    }
  }

  public static void main(String[] args) {
    final int[] substitutions = {2, 4, 8, 12, 5};
    final int expectedVal = 24;

    Set<String> resultSet = new FullEnumerator().getAllExpressions(Var.getVarList(substitutions.length)).stream()
        .filter(expression -> test(expression, expectedVal, substitutions))
        .map(expression -> expression.toString(substitutions) + " = " + expectedVal)
        .collect(Collectors.toSet());

    for (String result : resultSet)
      System.out.println(result);
  }
}
