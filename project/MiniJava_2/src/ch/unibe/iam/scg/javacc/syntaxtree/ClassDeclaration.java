/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.syntaxtree;

import ch.unibe.iam.scg.javacc.visitor.*;

/**
 * JTB node class for the production ClassDeclaration:<br>
 * Corresponding grammar:<br>
 * f0 -> <CLASS><br>
 * f1 -> Identifier()<br>
 * f2 -> ( #0 <EXTENDS> #1 Identifier() )?<br>
 * f3 -> <BRACE_LEFT><br>
 * f4 -> ( VarDeclaration() )*<br>
 * f5 -> ( MethodDeclaration() )*<br>
 * f6 -> <BRACE_RIGHT><br>
 */
public class ClassDeclaration implements INode {

  /** Child node 1 */
  public NodeToken f0;

  /** Child node 2 */
  public Identifier f1;

  /** Child node 3 */
  public NodeOptional f2;

  /** Child node 4 */
  public NodeToken f3;

  /** Child node 5 */
  public NodeListOptional f4;

  /** Child node 6 */
  public NodeListOptional f5;

  /** Child node 7 */
  public NodeToken f6;

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
  public ClassDeclaration(final NodeToken n0, final Identifier n1, final NodeOptional n2, final NodeToken n3, final NodeListOptional n4, final NodeListOptional n5, final NodeToken n6) {
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
