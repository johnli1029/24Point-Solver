public abstract class Expression {
  Operator operator;

  Expression() {
  }

  public abstract double evaluate(int a, int b, int c, int d);

  public static void main(String[] args) {
    System.out.println(new BiOperandExp(Operator.PLUS, new Var("A"), new Var("B")));
  }

}
