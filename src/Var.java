import java.util.ArrayList;
import java.util.List;

public class Var extends Expression {
  private char varName;

  public Var(char varName) {
    this(0, varName);
  }

  public Var(int id, char varName) {
    super(id, 0);
    this.operator = Operator.VAR;
    this.varName = varName;
  }

  public Var(Var another) {
    this(another.id, another.varName);
  }

  public static List<Expression> getVarList(int n) {
    List<Expression> varList = new ArrayList<>();
    for (int i = 0; i < n; i++)
      varList.add(new Var(i, (char) ('A' + i)));

    return varList;
  }

  @Override
  public double evaluate(int... substitutions) {
    int index = this.varName - 'A';
    if (substitutions.length <= index)
      throw new IllegalArgumentException("Unbound Variable");

    return substitutions[index];
  }

  @Override
  public Expression makeCopy() {
    return new Var(this);
  }

  @Override
  public String toString() {
    return String.valueOf(this.varName);
  }

  @Override
  public String toString(int... substitutions) {
    int index = this.varName - 'A';
    if (substitutions.length <= index)
      throw new IllegalArgumentException("Unbound Variable");

    return String.valueOf(substitutions[index]);
  }
}
