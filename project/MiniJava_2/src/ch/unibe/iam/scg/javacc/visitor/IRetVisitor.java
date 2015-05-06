/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.visitor;

import ch.unibe.iam.scg.javacc.syntaxtree.*;

/**
 * All "Ret" visitors must implement this interface.
 * @param <R> - The user return information type
 */
public interface IRetVisitor<R> {

  /*
   * Base "Ret" visit methods
   */

  /**
   * Visits a {@link NodeChoice} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeChoice n);

  /**
   * Visits a {@link NodeList} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeList n);

  /**
   * Visits a {@link NodeListOptional} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeListOptional n);

  /**
   * Visits a {@link NodeOptional} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeOptional n);

  /**
   * Visits a {@link NodeSequence} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeSequence n);

  /**
   * Visits a {@link NodeTCF} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeTCF n);

  /**
   * Visits a {@link NodeToken} node.
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final NodeToken n);

  /*
   * User grammar generated visit methods
   */

  /**
   * Visits a {@link Goal} node, whose children are the following :
   * <p>
   * f0 -> MainClass()<br>
   * f1 -> ( ClassDeclaration() )*<br>
   * f2 -> <EOF><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Goal n);

  /**
   * Visits a {@link MainClass} node, whose children are the following :
   * <p>
   * f0 -> <CLASS><br>
   * f1 -> Identifier()<br>
   * f2 -> <BRACE_LEFT><br>
   * f3 -> <PUBLIC_MODIFIER><br>
   * f4 -> <STATIC_MODIFIER><br>
   * f5 -> <VOID_TYPE><br>
   * f6 -> <MAIN_METHOD_NAME><br>
   * f7 -> <PARENTHESIS_LEFT><br>
   * f8 -> <STRING_TYPE><br>
   * f9 -> <BRACKET_LEFT><br>
   * f10 -> <BRACKET_RIGHT><br>
   * f11 -> Identifier()<br>
   * f12 -> <PARENTHESIS_RIGHT><br>
   * f13 -> <BRACE_LEFT><br>
   * f14 -> ( Statement() )?<br>
   * f15 -> <BRACE_RIGHT><br>
   * f16 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final MainClass n);

  /**
   * Visits a {@link ClassDeclaration} node, whose children are the following :
   * <p>
   * f0 -> <CLASS><br>
   * f1 -> Identifier()<br>
   * f2 -> ( #0 <EXTENDS> #1 Identifier() )?<br>
   * f3 -> <BRACE_LEFT><br>
   * f4 -> ( VarDeclaration() )*<br>
   * f5 -> ( MethodDeclaration() )*<br>
   * f6 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ClassDeclaration n);

  /**
   * Visits a {@link VarDeclaration} node, whose children are the following :
   * <p>
   * f0 -> TypedDeclaration()<br>
   * f1 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final VarDeclaration n);

  /**
   * Visits a {@link MethodDeclaration} node, whose children are the following :
   * <p>
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
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final MethodDeclaration n);

  /**
   * Visits a {@link ParameterDeclarationList} node, whose child is the following :
   * <p>
   * f0 -> ( #0 ParameterDeclaration()<br>
   * .. .. . #1 ( $0 <COMMA> $1 ParameterDeclaration() )* )?<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ParameterDeclarationList n);

  /**
   * Visits a {@link ParameterDeclaration} node, whose child is the following :
   * <p>
   * f0 -> TypedDeclaration()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ParameterDeclaration n);

  /**
   * Visits a {@link TypedDeclaration} node, whose children are the following :
   * <p>
   * f0 -> Type()<br>
   * f1 -> Identifier()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final TypedDeclaration n);

  /**
   * Visits a {@link Type} node, whose child is the following :
   * <p>
   * f0 -> . %0 IntArrayType()<br>
   * .. .. | %1 IntType()<br>
   * .. .. | %2 BooleanType()<br>
   * .. .. | %3 VoidType()<br>
   * .. .. | %4 Identifier()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Type n);

  /**
   * Visits a {@link Statement} node, whose child is the following :
   * <p>
   * f0 -> . %0 BlockStatement()<br>
   * .. .. | %1 IfStatement()<br>
   * .. .. | %2 WhileStatement()<br>
   * .. .. | %3 PrintStatement()<br>
   * .. .. | %4 AssignmentStatement()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Statement n);

  /**
   * Visits a {@link BlockStatement} node, whose children are the following :
   * <p>
   * f0 -> <BRACE_LEFT><br>
   * f1 -> ( Statement() )*<br>
   * f2 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final BlockStatement n);

  /**
   * Visits a {@link IfStatement} node, whose children are the following :
   * <p>
   * f0 -> <IF><br>
   * f1 -> <PARENTHESIS_LEFT><br>
   * f2 -> Expression()<br>
   * f3 -> <PARENTHESIS_RIGHT><br>
   * f4 -> Statement()<br>
   * f5 -> <ELSE><br>
   * f6 -> Statement()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final IfStatement n);

  /**
   * Visits a {@link WhileStatement} node, whose children are the following :
   * <p>
   * f0 -> <WHILE><br>
   * f1 -> <PARENTHESIS_LEFT><br>
   * f2 -> Expression()<br>
   * f3 -> <PARENTHESIS_RIGHT><br>
   * f4 -> Statement()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final WhileStatement n);

  /**
   * Visits a {@link PrintStatement} node, whose children are the following :
   * <p>
   * f0 -> <PRINT_METHOD><br>
   * f1 -> <PARENTHESIS_LEFT><br>
   * f2 -> Expression()<br>
   * f3 -> <PARENTHESIS_RIGHT><br>
   * f4 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final PrintStatement n);

  /**
   * Visits a {@link AssignmentStatement} node, whose children are the following :
   * <p>
   * f0 -> Assignee()<br>
   * f1 -> <EQUALS_SIGN><br>
   * f2 -> Expression()<br>
   * f3 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final AssignmentStatement n);

  /**
   * Visits a {@link Assignee} node, whose child is the following :
   * <p>
   * f0 -> . %0 AssignableArrayAccess()<br>
   * .. .. | %1 Identifier()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Assignee n);

  /**
   * Visits a {@link AssignableArrayAccess} node, whose children are the following :
   * <p>
   * f0 -> Identifier()<br>
   * f1 -> ArrayAccess()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final AssignableArrayAccess n);

  /**
   * Visits a {@link ArrayAccess} node, whose children are the following :
   * <p>
   * f0 -> <BRACKET_LEFT><br>
   * f1 -> Expression()<br>
   * f2 -> <BRACKET_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ArrayAccess n);

  /**
   * Visits a {@link Expression} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 ObjectInstantiationExpression() #1 ExpressionPrime()<br>
   * .. .. | %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()<br>
   * .. .. | %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()<br>
   * .. .. | %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()<br>
   * .. .. | %4 #0 <TRUE> #1 ExpressionPrime()<br>
   * .. .. | %5 #0 <FALSE> #1 ExpressionPrime()<br>
   * .. .. | %6 #0 <THIS> #1 ExpressionPrime()<br>
   * .. .. | %7 #0 Identifier() #1 ExpressionPrime()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Expression n);

  /**
   * Visits a {@link ObjectInstantiationExpression} node, whose children are the following :
   * <p>
   * f0 -> <NEW><br>
   * f1 -> ConstructorCall()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ObjectInstantiationExpression n);

  /**
   * Visits a {@link ConstructorCall} node, whose child is the following :
   * <p>
   * f0 -> . %0 ClassConstructorCall()<br>
   * .. .. | %1 IntArrayConstructorCall()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ConstructorCall n);

  /**
   * Visits a {@link ClassConstructorCall} node, whose children are the following :
   * <p>
   * f0 -> Identifier()<br>
   * f1 -> <PARENTHESIS_LEFT><br>
   * f2 -> <PARENTHESIS_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ClassConstructorCall n);

  /**
   * Visits a {@link IntArrayConstructorCall} node, whose children are the following :
   * <p>
   * f0 -> IntType()<br>
   * f1 -> ArrayAccess()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final IntArrayConstructorCall n);

  /**
   * Visits a {@link ExpressionPrime} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()<br>
   * .. .. | %1 #0 ArrayAccess() #1 ExpressionPrime()<br>
   * .. .. | %2 #0 ArrayLengthAccess() #1 ExpressionPrime()<br>
   * .. .. | %3 #0 MethodCall() #1 ExpressionPrime()<br>
   * .. .. | %4 Empty()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ExpressionPrime n);

  /**
   * Visits a {@link ArrayLengthAccess} node, whose children are the following :
   * <p>
   * f0 -> <DOT><br>
   * f1 -> <LENGTH_FIELD_NAME><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ArrayLengthAccess n);

  /**
   * Visits a {@link MethodCall} node, whose children are the following :
   * <p>
   * f0 -> <DOT><br>
   * f1 -> Identifier()<br>
   * f2 -> <PARENTHESIS_LEFT><br>
   * f3 -> ParameterList()<br>
   * f4 -> <PARENTHESIS_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final MethodCall n);

  /**
   * Visits a {@link ParameterList} node, whose child is the following :
   * <p>
   * f0 -> ( #0 Parameter()<br>
   * .. .. . #1 ( $0 <COMMA> $1 Parameter() )* )?<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final ParameterList n);

  /**
   * Visits a {@link Parameter} node, whose child is the following :
   * <p>
   * f0 -> Expression()<br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Parameter n);

  /**
   * Visits a {@link UnaryOperator} node, whose child is the following :
   * <p>
   * f0 -> <UNARY_OPERATOR><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final UnaryOperator n);

  /**
   * Visits a {@link BinaryOperator} node, whose child is the following :
   * <p>
   * f0 -> <BINARY_OPERATOR><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final BinaryOperator n);

  /**
   * Visits a {@link BooleanType} node, whose child is the following :
   * <p>
   * f0 -> <BOOLEAN_TYPE><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final BooleanType n);

  /**
   * Visits a {@link VoidType} node, whose child is the following :
   * <p>
   * f0 -> <VOID_TYPE><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final VoidType n);

  /**
   * Visits a {@link IntType} node, whose child is the following :
   * <p>
   * f0 -> <INT_TYPE><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final IntType n);

  /**
   * Visits a {@link IntArrayType} node, whose children are the following :
   * <p>
   * f0 -> <INT_TYPE><br>
   * f1 -> <BRACKET_LEFT><br>
   * f2 -> <BRACKET_RIGHT><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final IntArrayType n);

  /**
   * Visits a {@link Identifier} node, whose child is the following :
   * <p>
   * f0 -> <IDENTIFIER><br>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Identifier n);

  /**
   * Visits a {@link Empty} node, whose children are the following :
   * <p>
   *
   * @param n - the node to visit
   * @return the user return information
   */
  public R visit(final Empty n);

}
