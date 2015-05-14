/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.syntaxtree;

import ch.unibe.iam.scg.javacc.visitor.*;

/**
 * JTB node class for the production ExpressionPrime:<br>
 * Corresponding grammar:<br>
 * f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()<br>
 * .. .. | %1 #0 ArrayAccess() #1 ExpressionPrime()<br>
 * .. .. | %2 #0 ArrayLengthAccess() #1 ExpressionPrime()<br>
 * .. .. | %3 #0 MethodCall() #1 ExpressionPrime()<br>
 * .. .. | %4 Empty()<br>
 */
public class ExpressionPrime implements INode {

  /** Child node 1 */
  public NodeChoice f0;

  /** The serial version UID */
  private static final long serialVersionUID = 149L;

  /**
   * Constructs the node with its child node.
   *
   * @param n0 - the child node
   */
  public ExpressionPrime(final NodeChoice n0) {
    f0 = n0;
  }

  /**
   * Accepts the IRetArguVisitor visitor.
   *
   * @param <R> the user return type
   * @param <A> the user argument type
   * @param vis - the visitor
   * @param argu - a user chosen argument
   * @return a user chosen return information
   */
  @Override
  public <R, A> R accept(final IRetArguVisitor<R, A> vis, final A argu) {
    return vis.visit(this, argu);
  }

  /**
   * Accepts the IRetVisitor visitor.
   *
   * @param <R> the user return type
   * @param vis - the visitor
   * @return a user chosen return information
   */
  @Override
  public <R> R accept(final IRetVisitor<R> vis) {
    return vis.visit(this);
  }

  /**
   * Accepts the IVoidArguVisitor visitor.
   *
   * @param <A> the user argument type
   * @param vis - the visitor
   * @param argu - a user chosen argument
   */
  @Override
  public <A> void accept(final IVoidArguVisitor<A> vis, final A argu) {
    vis.visit(this, argu);
  }

  /**
   * Accepts the IVoidVisitor visitor.
   *
   * @param vis - the visitor
   */
  @Override
  public void accept(final IVoidVisitor vis) {
    vis.visit(this);
  }

}
