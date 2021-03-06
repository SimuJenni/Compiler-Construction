/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.syntaxtree;

import ch.unibe.iam.scg.javacc.visitor.*;

/**
 * JTB node class for the production IfStatement:<br>
 * Corresponding grammar:<br>
 * f0 -> <IF><br>
 * f1 -> <PARENTHESIS_LEFT><br>
 * f2 -> Expression()<br>
 * f3 -> <PARENTHESIS_RIGHT><br>
 * f4 -> Statement()<br>
 * f5 -> <ELSE><br>
 * f6 -> Statement()<br>
 */
public class IfStatement implements INode {

  /** Child node 1 */
  public NodeToken f0;

  /** Child node 2 */
  public NodeToken f1;

  /** Child node 3 */
  public Expression f2;

  /** Child node 4 */
  public NodeToken f3;

  /** Child node 5 */
  public Statement f4;

  /** Child node 6 */
  public NodeToken f5;

  /** Child node 7 */
  public Statement f6;

  /** The serial version UID */
  private static final long serialVersionUID = 149L;

  /**
   * Constructs the node with all its children nodes.
   *
   * @param n0 - first child node
   * @param n1 - next child node
   * @param n2 - next child node
   * @param n3 - next child node
   * @param n4 - next child node
   * @param n5 - next child node
   * @param n6 - next child node
   */
  public IfStatement(final NodeToken n0, final NodeToken n1, final Expression n2, final NodeToken n3, final Statement n4, final NodeToken n5, final Statement n6) {
    f0 = n0;
    f1 = n1;
    f2 = n2;
    f3 = n3;
    f4 = n4;
    f5 = n5;
    f6 = n6;
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
