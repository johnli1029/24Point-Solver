/**
 * The {@code BiOperandExp} class represents arithmetic expressions containing a two-operand operator,
 * which keeps the position of nodes in the expression tree.
 */
public class BiOperandExp extends Expression {
  Expression leftOperand;
  Expression rightOperand;

  public BiOperandExp(Operator operator, Expression leftOperand, Expression rightOperand) {
    this(0, operator, leftOperand, rightOperand, 0);
  }

  public BiOperandExp(int id, Operator operator, Expression leftOperand, Expression rightOperand) {
    this(id, operator, leftOperand, rightOperand, 0);
  }

  public BiOperandExp(Operator operator, Expression leftOperand, Expression rightOperand, int polar) {
    this(0, operator, leftOperand, rightOperand, polar);
  }

  public BiOperandExp(int id, Operator operator, Expression leftOperand, Expression rightOperand, int polar) {
    super(id, polar);
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  public BiOperandExp(BiOperandExp another) {
    this(another.id, another.operator, another.leftOperand, another.rightOperand, another.polar);
  }

  /**
   * Return the value of expression evaluated by the substitution list
   *
   * @param substitutions the evaluation mapping between variables and values
   * @return evaluation value
   */
  @Override
  public double evaluate(int... substitutions) {
    double leftVal = leftOperand.evaluate(substitutions);
    double rightVal = rightOperand.evaluate(substitutions);

    switch (operator) {
      case PLUS:
        return leftVal + rightVal;
      case MINUS:
        return leftVal - rightVal;
      case PRODUCT:
        return leftVal * rightVal;
      case DIVISION:
        if (rightVal / 1e-5 < 1) throw new ArithmeticException("Zero denominator");
        return leftVal / rightVal;
      default:
        throw new IllegalArgumentException("Illegal Operator.");
    }
  }

  /**
   * Clone method
   *
   * @return Deep cloned copy
   */
  @Override
  public Expression makeCopy() {
    return new BiOperandExp(this);
  }

  @Override
  public String toString() {
    String left = needLeftBracket() ? "(" + leftOperand + ")" : leftOperand.toString();
    String right = needRightBracket() ? "(" + rightOperand + ")" : rightOperand.toString();

    return left + " " + this.operator + " " + right;
  }

  @Override
  public String toString(int... substitutions) {
    String left = needLeftBracket() ? "(" + leftOperand.toString(substitutions) + ")" : leftOperand.toString(substitutions);
    String right = needRightBracket() ? "(" + rightOperand.toString(substitutions) + ")" : rightOperand.toString(substitutions);

    return left + " " + this.operator + " " + right;
  }

  /**
   * Returns whether the left operand needs to be covered by brackets
   */
  private boolean needLeftBracket() {
    return leftOperand.operator != Operator.VAR
        && (operator == Operator.PRODUCT || operator == Operator.DIVISION)
        && (leftOperand.operator == Operator.PLUS || leftOperand.operator == Operator.MINUS);
  }

  /**
   * Returns whether the left operand needs to be covered by brackets
   */
  private boolean needRightBracket() {
    return rightOperand.operator != Operator.VAR
        && ((operator == Operator.DIVISION)
        || ((operator == Operator.PRODUCT || operator == Operator.MINUS)
        && (rightOperand.operator == Operator.PLUS || rightOperand.operator == Operator.MINUS)));
  }
}
