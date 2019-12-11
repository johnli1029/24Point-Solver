public abstract class Expression {
  Operator operator;
  int id;
  int polar;

  Expression(int id, int polar) {
    this.id = id;
    this.polar = polar;
  }

  public abstract double evaluate(int a, int b, int c, int d);

  public abstract Expression makeCopy();

  public static void main(String[] args) {
    System.out.println(new BiOperandExp(3, Operator.PLUS, new Var(1, "A"), new Var(2, "B")));
  }

}
