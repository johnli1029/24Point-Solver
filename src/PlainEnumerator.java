import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PlainEnumerator {
  private List<Expression> allExpList = new LinkedList<>();

  public List<Expression> getAllExpressions(List<Expression> expList) {
    enumerate(expList);

    ArrayList<Expression> ret = new ArrayList<>();
    for (Expression exp : allExpList) {
      ret.add(exp.makeCopy());
    }
    allExpList.clear();
    return ret;
  }

  public void enumerate(List<Expression> expList) {
    if (expList.size() == 1) {
      allExpList.add(expList.get(0));
    } else {
      for (int i = 0; i < expList.size(); i++) {
        for (int j = i + 1; j < expList.size(); j++) {
          for (Expression newExp : combine(expList.get(i), expList.get(j))) {
            List<Expression> newExpList = new ArrayList<>();
            for (int k = 0; k < expList.size(); k++)
              if (k != i && k != j)
                newExpList.add(expList.get(k));

            newExpList.add(newExp);
            enumerate(newExpList);
          }
        }
      }
    }
  }

  private List<Expression> combine(Expression expA, Expression expB) {
    List<Expression> result = new ArrayList<>();
    for (Operator operator : new Operator[]{Operator.PLUS, Operator.MULTIPLY}) {
      result.add(new BiOperandExp(operator, expA, expB));
    }

    for (Operator operator : new Operator[]{Operator.MINUS, Operator.DIVISION}) {
      result.add(new BiOperandExp(operator, expA, expB));
      result.add(new BiOperandExp(operator, expB, expA));
    }

    return result;
  }

  public static void main(String[] args) {
    PlainEnumerator plainEnumerator = new PlainEnumerator();

    // Enumerate all valid expressions in 4-element 24 Point game
    int N = 4;
    List<Expression> expressionList = plainEnumerator.getAllExpressions(Arrays.asList(
        new Var("A"), new Var("B"), new Var("C"), new Var("D")));
    System.out.println(expressionList.size());
    System.out.println(Utils.factorial(N).intValue() * (int) Math.pow(4, N - 1) * Utils.factorial(N - 1).intValue());
  }

}
