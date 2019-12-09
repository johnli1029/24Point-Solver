import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Enumerator {
  public List<Expression> allExpList = new LinkedList<>();

  public void enumerateAll(List<Expression> expList) {
    if (expList.size() == 1)
      allExpList.add(expList.get(0));
    else {
      for (int i = 0; i < expList.size(); i++) {
        for (int j = i + 1; j < expList.size(); j++) {
          for (Expression newExp : combine(expList.get(i), expList.get(j))) {
            List<Expression> newExpList = new ArrayList<>();
            newExpList.add(newExp);
            for (int k = 0; k < expList.size(); k++)
              if (k != i && k != j)
                newExpList.add(expList.get(k));

            enumerateAll(newExpList);
          }
        }
      }
    }
  }

  private Expression[] combine(Expression expA, Expression expB) {
    Expression[] result = new Expression[8];
    int i = 0;
    for (Operator operator : new Operator[]{Operator.PLUS, Operator.MINUS, Operator.MULTIPLY, Operator.DIVISION}) {
      result[i++] = new BiOperandExp(operator, expA, expB);
      result[i++] = new BiOperandExp(operator, expB, expA);
    }

    return result;
  }

  public static int factorial(int n) {
    if (n == 1)
      return 1;
    else
      return n * factorial(n - 1);
  }

  public static void main(String[] args) {
    Enumerator enumerator = new Enumerator();

    // Enumerate all valid expressions in 4-element 24 Point game
    int N = 4;
    enumerator.enumerateAll(Arrays.asList(
        new Var("A"), new Var("B"), new Var("C"), new Var("D")));
    System.out.println(enumerator.allExpList);
    System.out.println(enumerator.allExpList.size());
    System.out.println(factorial(N) * (int) Math.pow(4, N - 1) * factorial(N - 1));
  }


}
