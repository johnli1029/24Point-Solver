public enum Operator {
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
  }, PRODUCT {
    @Override
    public String toString() {
      return "*";
    }
  }, DIVISION {
    @Override
    public String toString() {
      return String.valueOf((char) 0xF7);
    }
  }, VAR
}
