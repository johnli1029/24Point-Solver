import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class PlusMultiplyEnumerator {
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
    for (Operator operator : new Operator[]{Operator.PLUS, Operator.PRODUCT}) {
      if (expA.operator != operator && (expB.operator != operator || expA.id < ((BiOperandExp) expB).leftOperand.id))
        result.add(new BiOperandExp(operator, expA, expB));
    }

    return result;
  }
}
