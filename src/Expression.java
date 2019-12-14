/**
 * The {@code Expression} class represents an arithmetic expression
 * including four operators: +-*{@literal /} as well as parentheses.
 * The expression is expressed as a <em>BST-like structure</em>
 * rather than using {@code String} for the convenience of evaluation,
 * otherwise a <em>parser</em> is needed. Every expression can be expressed
 * as a corresponding tree, of which internal nodes stand for <em>operators</em> and
 * leaf nodes are <em>operands</em>.
 * <p>
 * In order to keep the extensibility as well as universality, the operands of the expression
 * are kept as in form of variables (i.e. characters) rather than specific numbers. The descendant classes
 * of {@code Expression} class must provide a {@code evaluation} function which accepts a series
 * of <em>variable substitutions</em>.
 * <p>
 * The {@code Expression} class has two subclasses: {@link BiOperandExp} which represents
 * a two-operand expression and {@link Var} which stands for a single variable.
 * Expressed in <em>Haskell</em> (without fields <i>id</i>, <i>polar</i>):
 * <p>
 * {@code Exp = BiOperatorExp Exp Exp | Var char}
 * <p>
 * The Expression structure, as well as all algorithms implemented in this project, is inspired by
 * <a href="https://zhuanlan.zhihu.com/p/33998387">王赟 Maigo's articles</a> posted on his blog.
 *
 * @author Haiyue Li
 * @version 1.0
 */

public abstract class Expression {
  protected Operator operator;
  protected int id;     // id of the expression. It's imported in order to get rid of the duplicate redundant expression
  protected int polar;  // polar of the expression.
  // It's introduced to indicate whether an expression can obtain its opposite one
  // by reverting the operands of (some of) its subtraction operators.

  Expression(int id, int polar) {
    this.id = id;
    this.polar = polar;
  }

  public abstract double evaluate(int... substitutions);

  /**
   * Returns a deep copy of the expression itself.
   *
   * @return a {@code Expression} class which is a clone obtained by deep copy
   */
  public abstract Expression makeCopy();

  public abstract String toString(int... substitutions);
}
