import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Var} class represents variables in arithmetic expressions,
 * which keeps the position of leaf in the expression tree.
 */
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

  /**
   * Return a list of n sequential variables.
   * Sequential means the variables have ascending variable names
   * in terms of Unicode as well as ids, starting from 0.
   *
   * @param   n the number of variables returned
   * @returna a list of sequential variables with length n.
   */
  public static List<Expression> getVarList(int n) {
    List<Expression> varList = new ArrayList<>();
    for (int i = 0; i < n; i++)
      varList.add(new Var(i, (char) ('A' + i)));

    return varList;
  }

  /**
   * Return the value of expression evaluated by the substitution list
   *
   * @param   substitutions the evaluation mapping between variables and values
   * @return  evaluation value
   */
  @Override
  public double evaluate(int... substitutions) {
    int index = this.varName - 'A';
    if (substitutions.length <= index)
      throw new IllegalArgumentException("Unbound Variable");

    return substitutions[index];
  }

  /**
   * Clone method
   *
   * @return  Deep cloned copy
   */
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
