public class Var extends Expression {
  String varName;

  public Var(String varName) {
    this.operator = Operator.VAR;
    this.varName = varName;
  }

  @Override
  public String toString() {
    return this.varName;
  }
}
