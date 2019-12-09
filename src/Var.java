public class Var extends Expression {
  String varName;

  public Var(String varName) {
    this.operator = Operator.VAR;
    this.varName = varName;
  }

  @Override
  public double evaluate(int a, int b, int c, int d) {
    switch (varName) {
      case "A":
        return a;
      case "B":
        return b;
      case "C":
        return c;
      case "D":
        return d;
      default:
        throw new IllegalArgumentException("Unbound Variable");
    }
  }

  @Override
  public String toString() {
    return this.varName;
  }
}
