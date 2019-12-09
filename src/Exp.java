enum Ops {
  PLUS {
    @Override
    public String toString() {
      return "+";
    }
  }, MINUS {
    @Override
    public String toString() {
      return "-";
    }
  }, MULTIPLY {
    @Override
    public String toString() {
      return "*";
    }
  }, DIVISION {
    @Override
    public String toString() {
      return "/";
    }
  }, VAR
}

public class Exp {
  private Ops operator;
  private Exp leftOperand;
  private Exp rightOperand;
  private String varName;

  public Exp(Ops operator, String varName) {
    assert operator == Ops.VAR;

    this.operator = Ops.VAR;
    this.varName = varName;
  }

  public Exp(Ops operator, Exp leftOperand, Exp rightOperand) {
    assert operator != Ops.VAR;

    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  @Override
  public String toString() {
    if (this.operator == Ops.VAR)
      return this.varName;
    else {
      boolean needLeftBracket = leftOperand.operator != Ops.VAR
          && (operator == Ops.MULTIPLY || operator == Ops.DIVISION)
          && (leftOperand.operator == Ops.PLUS || leftOperand.operator == Ops.MINUS);

      boolean needRightBracket = rightOperand.operator != Ops.VAR
          && ((operator == Ops.DIVISION)
              || ((operator == Ops.MULTIPLY || operator == Ops.MINUS)
                && (rightOperand.operator == Ops.PLUS || rightOperand.operator == Ops.MINUS)));

      String left = needLeftBracket ? "(" + leftOperand + ")" : leftOperand.toString();
      String right = needRightBracket ? "(" + rightOperand + ")" : rightOperand.toString();

      return left + " " + this.operator + " " + right;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Exp(Ops.PLUS, new Exp(Ops.VAR, "A"), new Exp(Ops.VAR, "B")));
  }


}
