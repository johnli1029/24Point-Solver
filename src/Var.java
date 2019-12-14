public class Var extends Expression {
  private String varName;

  public Var(String varName) {
    this(0, varName);
  }

  public Var(int id, String varName) {
    this(id, varName, 0);
  }

  public Var(String varName, int polar) {
    this(0, varName, polar);
  }

  public Var(int id, String varName, int polar) {
    super(id, polar);
    this.operator = Operator.VAR;
    this.varName = varName;
  }

  public Var(Var another) {
    this(another.id, another.varName, another.polar);
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
  public Expression makeCopy() {
    return new Var(this);
  }

  @Override
  public String toString() {
    return this.varName;
  }
}
