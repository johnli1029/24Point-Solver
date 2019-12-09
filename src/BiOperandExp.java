public class BiOperandExp extends Expression {
  Expression leftOperand;
  Expression rightOperand;

  public BiOperandExp(Operator operator, Expression leftOperand, Expression rightOperand) {
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public double evaluate(int a, int b, int c, int d) {
    double leftVal = leftOperand.evaluate(a, b, c, d);
    double rightVal = rightOperand.evaluate(a, b, c, d);

    switch (operator) {
      case PLUS:
        return leftVal + rightVal;
      case MINUS:
        return leftVal - rightVal;
      case MULTIPLY:
        return leftVal * rightVal;
      case DIVISION:
        return leftVal / rightVal;
      default:
        throw new IllegalArgumentException("Unbound Variable");
    }
  }

  @Override
  public String toString() {
    boolean needLeftBracket = leftOperand.operator != Operator.VAR
        && (operator == Operator.MULTIPLY || operator == Operator.DIVISION)
        && (leftOperand.operator == Operator.PLUS || leftOperand.operator == Operator.MINUS);

    boolean needRightBracket = rightOperand.operator != Operator.VAR
        && ((operator == Operator.DIVISION)
        || ((operator == Operator.MULTIPLY || operator == Operator.MINUS)
        && (rightOperand.operator == Operator.PLUS || rightOperand.operator == Operator.MINUS)));

    String left = needLeftBracket ? "(" + leftOperand + ")" : leftOperand.toString();
    String right = needRightBracket ? "(" + rightOperand + ")" : rightOperand.toString();

    return left + " " + this.operator + " " + right;
  }
}
