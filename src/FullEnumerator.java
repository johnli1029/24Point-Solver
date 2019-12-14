import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class FullEnumerator {
  private List<Expression> allExpList = new LinkedList<>();

  public List<Expression> getAllExpressions(List<Expression> expList) {
    enumerate(expList, 1);

    ArrayList<Expression> ret = new ArrayList<>();
    for (Expression exp : allExpList) {
      ret.add(exp.makeCopy());
    }
    allExpList.clear();
    return ret;
  }

  public void enumerate(List<Expression> expList, int minJ) {
    if (expList.size() == 1) {
      allExpList.add(expList.get(0));
    } else {
      for (int j = minJ; j < expList.size(); j++) {
        for (int i = 0; i < j; i++) {
          for (Expression newExp : combine(expList.get(i), expList.get(j))) {
            List<Expression> newExpList = new ArrayList<>();
            for (int k = 0; k < expList.size(); k++)
              if (k != i && k != j)
                newExpList.add(expList.get(k));

            newExp.id = expList.get(expList.size() - 1).id + 1;
            newExpList.add(newExp);
            enumerate(newExpList, j - 1);
          }
        }
      }
    }
  }

  private List<Expression> combine(Expression expA, Expression expB) {
    List<Expression> result = new ArrayList<>();

    // PLUS
    if (expA.operator != Operator.PLUS && expA.operator != Operator.MINUS
        && expB.operator != Operator.MINUS
        && (expB.operator != Operator.PLUS || expA.id < ((BiOperandExp) expB).leftOperand.id))
      result.add(new BiOperandExp(Operator.PLUS, expA, expB));

    // MINUS
    if (expA.operator != Operator.MINUS && expB.operator != Operator.MINUS) {
      result.add(new BiOperandExp(Operator.MINUS, expA, expB));
      result.add(new BiOperandExp(Operator.MINUS, expB, expA));
    }

    // MULTIPLY
    if (expA.operator != Operator.MULTIPLY && expA.operator != Operator.DIVISION
        && expB.operator != Operator.DIVISION
        && (expB.operator != Operator.MULTIPLY || expA.id < ((BiOperandExp) expB).leftOperand.id))
      result.add(new BiOperandExp(Operator.MULTIPLY, expA, expB));

    // DIVISION
    if (expA.operator != Operator.DIVISION && expB.operator != Operator.DIVISION) {
      result.add(new BiOperandExp(Operator.DIVISION, expA, expB));
      result.add(new BiOperandExp(Operator.DIVISION, expB, expA));
    }

    return result;
  }

  private static void display(List<Expression> allExpList) {
    for (Expression exp : allExpList)
      System.out.println(exp);
  }

  public static void main(String[] args) {
    FullEnumerator fullEnumerator = new FullEnumerator();

    List<Expression> allExps = fullEnumerator.getAllExpressions(Arrays.asList(
        new Var(0, "A"),
        new Var(1, "B"),
        new Var(2, "C"),
        new Var(3, "D")));

    display(allExps);
    System.out.println(allExps.size());
  }
}
