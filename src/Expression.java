public abstract class Expression {
  Operator operator;

  Expression() {
  }

  public static void main(String[] args) {
    System.out.println(new BiOperandExp(Operator.PLUS, new Var("A"), new Var("B")));
  }

}
