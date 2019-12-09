import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Enumerator {
  public List<Exp> allExpList = new LinkedList<>();

  public void enumerateAll(List<Exp> expList) {
    if (expList.size() == 1)
      allExpList.add(expList.get(0));
    else {
      for (int i = 0; i < expList.size(); i++) {
        for (int j = i + 1; j < expList.size(); j++) {
          for (Exp newExp : combine(expList.get(i), expList.get(j))) {
            List<Exp> newExpList = new ArrayList<>();
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

  private Exp[] combine(Exp expA, Exp expB) {
    Exp[] result = new Exp[8];
    int i = 0;
    for (Ops operator : new Ops[]{Ops.PLUS, Ops.MINUS, Ops.MULTIPLY, Ops.DIVISION}) {
      result[i++] = new Exp(operator, expA, expB);
      result[i++] = new Exp(operator, expB, expA);
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
    int N = 4;
    enumerator.enumerateAll(Arrays.asList(new Exp(Ops.VAR, "A"), new Exp(Ops.VAR, "B"), new Exp(Ops.VAR, "C"), new Exp(Ops.VAR, "D")));
    System.out.println(enumerator.allExpList);
    System.out.println(enumerator.allExpList.size());
    System.out.println(factorial(N) * (int) Math.pow(4, N - 1) * factorial(N - 1));
  }


}
