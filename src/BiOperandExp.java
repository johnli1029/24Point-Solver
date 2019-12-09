public class BiOperandExp extends Expression {
  Expression leftOperand;
  Expression rightOperand;

  public BiOperandExp(Operator operator, Expression leftOperand, Expression rightOperand) {
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
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
