import java.util.Arrays;

public class Evaluation {
  public static void test(Expression exp, int a, int b, int c, int d) {
    System.out.println((int) exp.evaluate(a, b, c, d));
  }

  public static void main(String[] args) {
    new Enumerator().getAllExpressions(Arrays.asList(
        new Var("A"), new Var("B"), new Var("C"))).stream()
        .peek(exp -> System.out.print(exp + " = "))
        .map(exp -> (int) exp.evaluate(1, 2, 3, 4))
        .forEach(System.out::println);
  }
}
