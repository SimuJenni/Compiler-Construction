/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.syntaxtree;

import ch.unibe.iam.scg.javacc.visitor.*;

/**
 * JTB node class for the production MethodDeclaration:<br>
 * Corresponding grammar:<br>
 * f0 -> <PUBLIC_MODIFIER><br>
 * f1 -> TypedDeclaration()<br>
 * f2 -> <PARENTHESIS_LEFT><br>
 * f3 -> ParameterDeclarationList()<br>
 * f4 -> <PARENTHESIS_RIGHT><br>
 * f5 -> <BRACE_LEFT><br>
 * f6 -> ( VarDeclaration() )*<br>
 * f7 -> ( Statement() )*<br>
 * f8 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?<br>
 * f9 -> <BRACE_RIGHT><br>
 */
public class MethodDeclaration implements INode {

  /** Child node 1 */
  public NodeToken f0;

  /** Child node 2 */
  public TypedDeclaration f1;

  /** Child node 3 */
  public NodeToken f2;

  /** Child node 4 */
  public ParameterDeclarationList f3;

  /** Child node 5 */
  public NodeToken f4;

  /** Child node 6 */
  public NodeToken f5;

  /** Child node 7 */
  public NodeListOptional f6;

  /** Child node 8 */
  public NodeListOptional f7;

  /** Child node 9 */
  public NodeOptional f8;

  /** Child node 10 */
  public NodeToken f9;

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
   * @param n7 - next child node
   * @param n8 - next child node
   * @param n9 - next child node
   */
  public MethodDeclaration(final NodeToken n0, final TypedDeclaration n1, final NodeToken n2, final ParameterDeclarationList n3, final NodeToken n4, final NodeToken n5, final NodeListOptional n6, final NodeListOptional n7, final NodeOptional n8, final NodeToken n9) {
    f0 = n0;
    f1 = n1;
    f2 = n2;
    f3 = n3;
    f4 = n4;
    f5 = n5;
    f6 = n6;
    f7 = n7;
    f8 = n8;
    f9 = n9;
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
