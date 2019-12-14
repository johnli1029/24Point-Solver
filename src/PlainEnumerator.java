import java.util.ArrayList;
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

  private void enumerate(List<Expression> expList) {
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
    for (Operator operator : new Operator[]{Operator.PLUS, Operator.PRODUCT}) {
      result.add(new BiOperandExp(operator, expA, expB));
    }

    for (Operator operator : new Operator[]{Operator.MINUS, Operator.DIVISION}) {
      result.add(new BiOperandExp(operator, expA, expB));
      result.add(new BiOperandExp(operator, expB, expA));
    }

    return result;
  }
}
