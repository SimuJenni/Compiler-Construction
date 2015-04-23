/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.visitor;

import ch.unibe.iam.scg.javacc.syntaxtree.*;
import ch.unibe.iam.scg.minijava.typechecker.Operator;

import java.util.*;


/**
 * Provides default methods which visit each node in the tree in depth-first order.<br>
 * In your "Void" visitors extend this class and override part or all of these methods.
 *
 */
public class DepthFirstVoidVisitor implements IVoidVisitor {


  /*
   * Base nodes classes visit methods (to be overridden if necessary)
   */

  /**
   * Visits a {@link NodeChoice} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeChoice n) {
    n.choice.accept(this);
    return;
  }

  /**
   * Visits a {@link NodeList} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeList n) {
    for (final Iterator<INode> e = n.elements(); e.hasNext();) {
      e.next().accept(this);
    }
    return;
  }

  /**
   * Visits a {@link NodeListOptional} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeListOptional n) {
    if (n.present()) {
      for (final Iterator<INode> e = n.elements(); e.hasNext();) {
        e.next().accept(this);
        }
      return;
    } else
      return;
  }

  /**
   * Visits a {@link NodeOptional} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeOptional n) {
    if (n.present()) {
      n.node.accept(this);
      return;
    } else
      return;
  }

  /**
   * Visits a {@link NodeSequence} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeSequence n) {
    for (final Iterator<INode> e = n.elements(); e.hasNext();) {
      e.next().accept(this);
    }
    return;
  }

  /**
   * Visits a {@link NodeTCF} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeTCF n) {
    @SuppressWarnings("unused")
    final String tkIm = n.tokenImage;
    return;
  }

  /**
   * Visits a {@link NodeToken} node.
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final NodeToken n) {
    @SuppressWarnings("unused")
    final String tkIm = n.tokenImage;
    return;
  }

  /*
   * User grammar generated visit methods (to be overridden if necessary)
   */

  /**
   * Visits a {@link Goal} node, whose children are the following :
   * <p>
   * f0 -> MainClass()<br>
   * f1 -> ( ClassDeclaration() )*<br>
   * f2 -> <EOF><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Goal n) {
    // f0 -> MainClass()
    final MainClass n0 = n.f0;
    n0.accept(this);
    // f1 -> ( ClassDeclaration() )*
    final NodeListOptional n1 = n.f1;
    if (n1.present()) {
      for (int i = 0; i < n1.size(); i++) {
        final INode nloeai = n1.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f2 -> <EOF>
    final NodeToken n2 = n.f2;
    n2.accept(this);
  }

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
   */
  @Override
  public void visit(final MainClass n) {
    // f0 -> <CLASS>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    n1.accept(this);
    // f2 -> <BRACE_LEFT>
    final NodeToken n2 = n.f2;
    n2.accept(this);
    // f3 -> <PUBLIC_MODIFIER>
    final NodeToken n3 = n.f3;
    n3.accept(this);
    // f4 -> <STATIC_MODIFIER>
    final NodeToken n4 = n.f4;
    n4.accept(this);
    // f5 -> <VOID_TYPE>
    final NodeToken n5 = n.f5;
    n5.accept(this);
    // f6 -> <MAIN_METHOD_NAME>
    final NodeToken n6 = n.f6;
    n6.accept(this);
    // f7 -> <PARENTHESIS_LEFT>
    final NodeToken n7 = n.f7;
    n7.accept(this);
    // f8 -> <STRING_TYPE>
    final NodeToken n8 = n.f8;
    n8.accept(this);
    // f9 -> <BRACKET_LEFT>
    final NodeToken n9 = n.f9;
    n9.accept(this);
    // f10 -> <BRACKET_RIGHT>
    final NodeToken n10 = n.f10;
    n10.accept(this);
    // f11 -> Identifier()
    final Identifier n11 = n.f11;
    n11.accept(this);
    // f12 -> <PARENTHESIS_RIGHT>
    final NodeToken n12 = n.f12;
    n12.accept(this);
    // f13 -> <BRACE_LEFT>
    final NodeToken n13 = n.f13;
    n13.accept(this);
    // f14 -> ( Statement() )?
    final NodeOptional n14 = n.f14;
    if (n14.present()) {
      n14.accept(this);
    }
    // f15 -> <BRACE_RIGHT>
    final NodeToken n15 = n.f15;
    n15.accept(this);
    // f16 -> <BRACE_RIGHT>
    final NodeToken n16 = n.f16;
    n16.accept(this);
  }

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
   */
  @Override
  public void visit(final ClassDeclaration n) {
    // f0 -> <CLASS>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    n1.accept(this);
    // f2 -> ( #0 <EXTENDS> #1 Identifier() )?
    final NodeOptional n2 = n.f2;
    if (n2.present()) {
      final NodeSequence seq = (NodeSequence) n2.node;
      // #0 <EXTENDS>
      final INode seq1 = seq.elementAt(0);
      seq1.accept(this);
      // #1 Identifier()
      final INode seq2 = seq.elementAt(1);
      seq2.accept(this);
    }
    // f3 -> <BRACE_LEFT>
    final NodeToken n3 = n.f3;
    n3.accept(this);
    // f4 -> ( VarDeclaration() )*
    final NodeListOptional n4 = n.f4;
    if (n4.present()) {
      for (int i = 0; i < n4.size(); i++) {
        final INode nloeai = n4.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f5 -> ( MethodDeclaration() )*
    final NodeListOptional n5 = n.f5;
    if (n5.present()) {
      for (int i = 0; i < n5.size(); i++) {
        final INode nloeai = n5.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f6 -> <BRACE_RIGHT>
    final NodeToken n6 = n.f6;
    n6.accept(this);
  }

  /**
   * Visits a {@link VarDeclaration} node, whose children are the following :
   * <p>
   * f0 -> TypedDeclaration()<br>
   * f1 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final VarDeclaration n) {
    // f0 -> TypedDeclaration()
    final TypedDeclaration n0 = n.f0;
    n0.accept(this);
    // f1 -> <SEMICOLON>
    final NodeToken n1 = n.f1;
    n1.accept(this);
  }

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
   */
  @Override
  public void visit(final MethodDeclaration n) {
    // f0 -> <PUBLIC_MODIFIER>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> TypedDeclaration()
    final TypedDeclaration n1 = n.f1;
    n1.accept(this);
    // f2 -> <PARENTHESIS_LEFT>
    final NodeToken n2 = n.f2;
    n2.accept(this);
    // f3 -> ParameterDeclarationList()
    final ParameterDeclarationList n3 = n.f3;
    n3.accept(this);
    // f4 -> <PARENTHESIS_RIGHT>
    final NodeToken n4 = n.f4;
    n4.accept(this);
    // f5 -> <BRACE_LEFT>
    final NodeToken n5 = n.f5;
    n5.accept(this);
    // f6 -> ( VarDeclaration() )*
    final NodeListOptional n6 = n.f6;
    if (n6.present()) {
      for (int i = 0; i < n6.size(); i++) {
        final INode nloeai = n6.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f7 -> ( Statement() )*
    final NodeListOptional n7 = n.f7;
    if (n7.present()) {
      for (int i = 0; i < n7.size(); i++) {
        final INode nloeai = n7.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f8 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
    final NodeOptional n8 = n.f8;
    if (n8.present()) {
      final NodeSequence seq = (NodeSequence) n8.node;
      // #0 <RETURN>
      final INode seq1 = seq.elementAt(0);
      seq1.accept(this);
      // #1 Expression()
      final INode seq2 = seq.elementAt(1);
      seq2.accept(this);
      // #2 <SEMICOLON>
      final INode seq3 = seq.elementAt(2);
      seq3.accept(this);
    }
    // f9 -> <BRACE_RIGHT>
    final NodeToken n9 = n.f9;
    n9.accept(this);
  }

  /**
   * Visits a {@link ParameterDeclarationList} node, whose child is the following :
   * <p>
   * f0 -> ( #0 ParameterDeclaration()<br>
   * .. .. . #1 ( $0 <COMMA> $1 ParameterDeclaration() )* )?<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ParameterDeclarationList n) {
    // f0 -> ( #0 ParameterDeclaration()
    // .. .. . #1 ( $0 <COMMA> $1 ParameterDeclaration() )* )?
    final NodeOptional n0 = n.f0;
    if (n0.present()) {
      final NodeSequence seq = (NodeSequence) n0.node;
      // #0 ParameterDeclaration()
      final INode seq1 = seq.elementAt(0);
      seq1.accept(this);
      // #1 ( $0 <COMMA> $1 ParameterDeclaration() )*
      final INode seq2 = seq.elementAt(1);
      final NodeListOptional nlo = (NodeListOptional) seq2;
      if (nlo.present()) {
        for (int i = 0; i < nlo.size(); i++) {
          final INode nloeai = nlo.elementAt(i);
          final NodeSequence seq3 = (NodeSequence) nloeai;
          // $0 <COMMA>
          final INode seq4 = seq3.elementAt(0);
          seq4.accept(this);
          // $1 ParameterDeclaration()
          final INode seq5 = seq3.elementAt(1);
          seq5.accept(this);
        }
      }
    }
  }

  /**
   * Visits a {@link ParameterDeclaration} node, whose child is the following :
   * <p>
   * f0 -> TypedDeclaration()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ParameterDeclaration n) {
    // f0 -> TypedDeclaration()
    final TypedDeclaration n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link TypedDeclaration} node, whose children are the following :
   * <p>
   * f0 -> Type()<br>
   * f1 -> Identifier()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final TypedDeclaration n) {
    // f0 -> Type()
    final Type n0 = n.f0;
    n0.accept(this);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    n1.accept(this);
  }

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
   */
  @Override
  public void visit(final Type n) {
    // f0 -> . %0 IntArrayType()
    // .. .. | %1 IntType()
    // .. .. | %2 BooleanType()
    // .. .. | %3 VoidType()
    // .. .. | %4 Identifier()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 IntArrayType()
        ich.accept(this);
        break;
      case 1:
        // %1 IntType()
        ich.accept(this);
        break;
      case 2:
        // %2 BooleanType()
        ich.accept(this);
        break;
      case 3:
        // %3 VoidType()
        ich.accept(this);
        break;
      case 4:
        // %4 Identifier()
        ich.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link Statement} node, whose child is the following :
   * <p>
   * f0 -> . %0 StatementList()<br>
   * .. .. | %1 If()<br>
   * .. .. | %2 WhileLoop()<br>
   * .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON><br>
   * .. .. | %4 Assignment()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Statement n) {
    // f0 -> . %0 StatementList()
    // .. .. | %1 If()
    // .. .. | %2 WhileLoop()
    // .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
    // .. .. | %4 Assignment()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 StatementList()
        ich.accept(this);
        break;
      case 1:
        // %1 If()
        ich.accept(this);
        break;
      case 2:
        // %2 WhileLoop()
        ich.accept(this);
        break;
      case 3:
        // %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
        final NodeSequence seq = (NodeSequence) ich;
        // #0 <PRINT_METHOD>
        final INode seq1 = seq.elementAt(0);
        seq1.accept(this);
        // #1 <PARENTHESIS_LEFT>
        final INode seq2 = seq.elementAt(1);
        seq2.accept(this);
        // #2 Expression()
        final INode seq3 = seq.elementAt(2);
        seq3.accept(this);
        // #3 <PARENTHESIS_RIGHT>
        final INode seq4 = seq.elementAt(3);
        seq4.accept(this);
        // #4 <SEMICOLON>
        final INode seq5 = seq.elementAt(4);
        seq5.accept(this);
        break;
      case 4:
        // %4 Assignment()
        ich.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link Assignment} node, whose children are the following :
   * <p>
   * f0 -> Assignee()<br>
   * f1 -> <EQUALS_SIGN><br>
   * f2 -> Expression()<br>
   * f3 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Assignment n) {
    // f0 -> Assignee()
    final Assignee n0 = n.f0;
    n0.accept(this);
    // f1 -> <EQUALS_SIGN>
    final NodeToken n1 = n.f1;
    n1.accept(this);
    // f2 -> Expression()
    final Expression n2 = n.f2;
    n2.accept(this);
    // f3 -> <SEMICOLON>
    final NodeToken n3 = n.f3;
    n3.accept(this);
  }

  /**
   * Visits a {@link Assignee} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT><br>
   * .. .. | %1 Identifier()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Assignee n) {
    // f0 -> . %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
    // .. .. | %1 Identifier()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
        final NodeSequence seq = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq1 = seq.elementAt(0);
        seq1.accept(this);
        // #1 <BRACKET_LEFT>
        final INode seq2 = seq.elementAt(1);
        seq2.accept(this);
        // #2 Expression()
        final INode seq3 = seq.elementAt(2);
        seq3.accept(this);
        // #3 <BRACKET_RIGHT>
        final INode seq4 = seq.elementAt(3);
        seq4.accept(this);
        break;
      case 1:
        // %1 Identifier()
        ich.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link WhileLoop} node, whose children are the following :
   * <p>
   * f0 -> <WHILE><br>
   * f1 -> <PARENTHESIS_LEFT><br>
   * f2 -> Expression()<br>
   * f3 -> <PARENTHESIS_RIGHT><br>
   * f4 -> Statement()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final WhileLoop n) {
    // f0 -> <WHILE>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> <PARENTHESIS_LEFT>
    final NodeToken n1 = n.f1;
    n1.accept(this);
    // f2 -> Expression()
    final Expression n2 = n.f2;
    n2.accept(this);
    // f3 -> <PARENTHESIS_RIGHT>
    final NodeToken n3 = n.f3;
    n3.accept(this);
    // f4 -> Statement()
    final Statement n4 = n.f4;
    n4.accept(this);
  }

  /**
   * Visits a {@link If} node, whose children are the following :
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
   */
  @Override
  public void visit(final If n) {
    // f0 -> <IF>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> <PARENTHESIS_LEFT>
    final NodeToken n1 = n.f1;
    n1.accept(this);
    // f2 -> Expression()
    final Expression n2 = n.f2;
    n2.accept(this);
    // f3 -> <PARENTHESIS_RIGHT>
    final NodeToken n3 = n.f3;
    n3.accept(this);
    // f4 -> Statement()
    final Statement n4 = n.f4;
    n4.accept(this);
    // f5 -> <ELSE>
    final NodeToken n5 = n.f5;
    n5.accept(this);
    // f6 -> Statement()
    final Statement n6 = n.f6;
    n6.accept(this);
  }

  /**
   * Visits a {@link StatementList} node, whose children are the following :
   * <p>
   * f0 -> <BRACE_LEFT><br>
   * f1 -> ( Statement() )*<br>
   * f2 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final StatementList n) {
    // f0 -> <BRACE_LEFT>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> ( Statement() )*
    final NodeListOptional n1 = n.f1;
    if (n1.present()) {
      for (int i = 0; i < n1.size(); i++) {
        final INode nloeai = n1.elementAt(i);
        nloeai.accept(this);
      }
    }
    // f2 -> <BRACE_RIGHT>
    final NodeToken n2 = n.f2;
    n2.accept(this);
  }

  /**
   * Visits a {@link Expression} node, whose child is the following :
   * <p>
   * f0 -> . %0 ObjectCreationExpression()<br>
   * .. .. | %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()<br>
   * .. .. | %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()<br>
   * .. .. | %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()<br>
   * .. .. | %4 #0 <TRUE> #1 ExpressionPrime()<br>
   * .. .. | %5 #0 <FALSE> #1 ExpressionPrime()<br>
   * .. .. | %6 #0 <THIS> #1 ExpressionPrime()<br>
   * .. .. | %7 #0 Identifier() #1 ExpressionPrime()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Expression n) {
    // f0 -> . %0 ObjectCreationExpression()
    // .. .. | %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()
    // .. .. | %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()
    // .. .. | %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
    // .. .. | %4 #0 <TRUE> #1 ExpressionPrime()
    // .. .. | %5 #0 <FALSE> #1 ExpressionPrime()
    // .. .. | %6 #0 <THIS> #1 ExpressionPrime()
    // .. .. | %7 #0 Identifier() #1 ExpressionPrime()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 ObjectCreationExpression()
        ich.accept(this);
        break;
      case 1:
        // %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()
        final NodeSequence seq = (NodeSequence) ich;
        // #0 UnaryOperator()
        final INode seq1 = seq.elementAt(0);
        seq1.accept(this);
        // #1 Expression()
        final INode seq2 = seq.elementAt(1);
        seq2.accept(this);
        // #2 ExpressionPrime()
        final INode seq3 = seq.elementAt(2);
        seq3.accept(this);
        break;
      case 2:
        // %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()
        final NodeSequence seq4 = (NodeSequence) ich;
        // #0 <PARENTHESIS_LEFT>
        final INode seq5 = seq4.elementAt(0);
        seq5.accept(this);
        // #1 Expression()
        final INode seq6 = seq4.elementAt(1);
        seq6.accept(this);
        // #2 <PARENTHESIS_RIGHT>
        final INode seq7 = seq4.elementAt(2);
        seq7.accept(this);
        // #3 ExpressionPrime()
        final INode seq8 = seq4.elementAt(3);
        seq8.accept(this);
        break;
      case 3:
        // %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
        final NodeSequence seq9 = (NodeSequence) ich;
        // #0 <INTEGER_LITERAL>
        final INode seq10 = seq9.elementAt(0);
        seq10.accept(this);
        // #1 ExpressionPrime()
        final INode seq11 = seq9.elementAt(1);
        seq11.accept(this);
        break;
      case 4:
        // %4 #0 <TRUE> #1 ExpressionPrime()
        final NodeSequence seq12 = (NodeSequence) ich;
        // #0 <TRUE>
        final INode seq13 = seq12.elementAt(0);
        seq13.accept(this);
        // #1 ExpressionPrime()
        final INode seq14 = seq12.elementAt(1);
        seq14.accept(this);
        break;
      case 5:
        // %5 #0 <FALSE> #1 ExpressionPrime()
        final NodeSequence seq15 = (NodeSequence) ich;
        // #0 <FALSE>
        final INode seq16 = seq15.elementAt(0);
        seq16.accept(this);
        // #1 ExpressionPrime()
        final INode seq17 = seq15.elementAt(1);
        seq17.accept(this);
        break;
      case 6:
        // %6 #0 <THIS> #1 ExpressionPrime()
        final NodeSequence seq18 = (NodeSequence) ich;
        // #0 <THIS>
        final INode seq19 = seq18.elementAt(0);
        seq19.accept(this);
        // #1 ExpressionPrime()
        final INode seq20 = seq18.elementAt(1);
        seq20.accept(this);
        break;
      case 7:
        // %7 #0 Identifier() #1 ExpressionPrime()
        final NodeSequence seq21 = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq22 = seq21.elementAt(0);
        seq22.accept(this);
        // #1 ExpressionPrime()
        final INode seq23 = seq21.elementAt(1);
        seq23.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link ObjectCreationExpression} node, whose children are the following :
   * <p>
   * f0 -> <NEW><br>
   * f1 -> ConstructorCall()<br>
   * f2 -> ExpressionPrime()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ObjectCreationExpression n) {
    // f0 -> <NEW>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> ConstructorCall()
    final ConstructorCall n1 = n.f1;
    n1.accept(this);
    // f2 -> ExpressionPrime()
    final ExpressionPrime n2 = n.f2;
    n2.accept(this);
  }

  /**
   * Visits a {@link ConstructorCall} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 Identifier() #1 <PARENTHESIS_LEFT> #2 <PARENTHESIS_RIGHT><br>
   * .. .. | %1 #0 IntType() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ConstructorCall n) {
    // f0 -> . %0 #0 Identifier() #1 <PARENTHESIS_LEFT> #2 <PARENTHESIS_RIGHT>
    // .. .. | %1 #0 IntType() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 #0 Identifier() #1 <PARENTHESIS_LEFT> #2 <PARENTHESIS_RIGHT>
        final NodeSequence seq = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq1 = seq.elementAt(0);
        seq1.accept(this);
        // #1 <PARENTHESIS_LEFT>
        final INode seq2 = seq.elementAt(1);
        seq2.accept(this);
        // #2 <PARENTHESIS_RIGHT>
        final INode seq3 = seq.elementAt(2);
        seq3.accept(this);
        break;
      case 1:
        // %1 #0 IntType() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
        final NodeSequence seq4 = (NodeSequence) ich;
        // #0 IntType()
        final INode seq5 = seq4.elementAt(0);
        seq5.accept(this);
        // #1 <BRACKET_LEFT>
        final INode seq6 = seq4.elementAt(1);
        seq6.accept(this);
        // #2 Expression()
        final INode seq7 = seq4.elementAt(2);
        seq7.accept(this);
        // #3 <BRACKET_RIGHT>
        final INode seq8 = seq4.elementAt(3);
        seq8.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link ExpressionPrime} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()<br>
   * .. .. | %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()<br>
   * .. .. | %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()<br>
   * .. .. | %3 MethodCall()<br>
   * .. .. | %4 Empty()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ExpressionPrime n) {
    // f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()
    // .. .. | %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()
    // .. .. | %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()
    // .. .. | %3 MethodCall()
    // .. .. | %4 Empty()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()
        final NodeSequence seq = (NodeSequence) ich;
        // #0 BinaryOperator()
        final INode seq1 = seq.elementAt(0);
        seq1.accept(this);
        // #1 Expression()
        final INode seq2 = seq.elementAt(1);
        seq2.accept(this);
        // #2 ExpressionPrime()
        final INode seq3 = seq.elementAt(2);
        seq3.accept(this);
        break;
      case 1:
        // %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()
        final NodeSequence seq4 = (NodeSequence) ich;
        // #0 <BRACKET_LEFT>
        final INode seq5 = seq4.elementAt(0);
        seq5.accept(this);
        // #1 Expression()
        final INode seq6 = seq4.elementAt(1);
        seq6.accept(this);
        // #2 <BRACKET_RIGHT>
        final INode seq7 = seq4.elementAt(2);
        seq7.accept(this);
        // #3 ExpressionPrime()
        final INode seq8 = seq4.elementAt(3);
        seq8.accept(this);
        break;
      case 2:
        // %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()
        final NodeSequence seq9 = (NodeSequence) ich;
        // #0 <DOT>
        final INode seq10 = seq9.elementAt(0);
        seq10.accept(this);
        // #1 <LENGTH_FIELD_NAME>
        final INode seq11 = seq9.elementAt(1);
        seq11.accept(this);
        // #2 ExpressionPrime()
        final INode seq12 = seq9.elementAt(2);
        seq12.accept(this);
        break;
      case 3:
        // %3 MethodCall()
        ich.accept(this);
        break;
      case 4:
        // %4 Empty()
        ich.accept(this);
        break;
      default:
        // should not occur !!!
        break;
    }
  }

  /**
   * Visits a {@link MethodCall} node, whose children are the following :
   * <p>
   * f0 -> <DOT><br>
   * f1 -> Identifier()<br>
   * f2 -> <PARENTHESIS_LEFT><br>
   * f3 -> ParameterList()<br>
   * f4 -> <PARENTHESIS_RIGHT><br>
   * f5 -> ExpressionPrime()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final MethodCall n) {
    // f0 -> <DOT>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    n1.accept(this);
    // f2 -> <PARENTHESIS_LEFT>
    final NodeToken n2 = n.f2;
    n2.accept(this);
    // f3 -> ParameterList()
    final ParameterList n3 = n.f3;
    n3.accept(this);
    // f4 -> <PARENTHESIS_RIGHT>
    final NodeToken n4 = n.f4;
    n4.accept(this);
    // f5 -> ExpressionPrime()
    final ExpressionPrime n5 = n.f5;
    n5.accept(this);
  }

  /**
   * Visits a {@link ParameterList} node, whose child is the following :
   * <p>
   * f0 -> ( #0 Parameter()<br>
   * .. .. . #1 ( $0 <COMMA> $1 Parameter() )* )?<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final ParameterList n) {
    // f0 -> ( #0 Parameter()
    // .. .. . #1 ( $0 <COMMA> $1 Parameter() )* )?
    final NodeOptional n0 = n.f0;
    if (n0.present()) {
      final NodeSequence seq = (NodeSequence) n0.node;
      // #0 Parameter()
      final INode seq1 = seq.elementAt(0);
      seq1.accept(this);
      // #1 ( $0 <COMMA> $1 Parameter() )*
      final INode seq2 = seq.elementAt(1);
      final NodeListOptional nlo = (NodeListOptional) seq2;
      if (nlo.present()) {
        for (int i = 0; i < nlo.size(); i++) {
          final INode nloeai = nlo.elementAt(i);
          final NodeSequence seq3 = (NodeSequence) nloeai;
          // $0 <COMMA>
          final INode seq4 = seq3.elementAt(0);
          seq4.accept(this);
          // $1 Parameter()
          final INode seq5 = seq3.elementAt(1);
          seq5.accept(this);
        }
      }
    }
  }

  /**
   * Visits a {@link Parameter} node, whose child is the following :
   * <p>
   * f0 -> Expression()<br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Parameter n) {
    // f0 -> Expression()
    final Expression n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link UnaryOperator} node, whose child is the following :
   * <p>
   * f0 -> <UNARY_OPERATOR><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final UnaryOperator n) {
    // f0 -> <UNARY_OPERATOR>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link BinaryOperator} node, whose child is the following :
   * <p>
   * f0 -> <BINARY_OPERATOR><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final BinaryOperator n) {
    // f0 -> <BINARY_OPERATOR>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link BooleanType} node, whose child is the following :
   * <p>
   * f0 -> <BOOLEAN_TYPE><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final BooleanType n) {
    // f0 -> <BOOLEAN_TYPE>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link VoidType} node, whose child is the following :
   * <p>
   * f0 -> <VOID_TYPE><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final VoidType n) {
    // f0 -> <VOID_TYPE>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link IntType} node, whose child is the following :
   * <p>
   * f0 -> <INT_TYPE><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final IntType n) {
    // f0 -> <INT_TYPE>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link IntArrayType} node, whose children are the following :
   * <p>
   * f0 -> <INT_TYPE><br>
   * f1 -> <BRACKET_LEFT><br>
   * f2 -> <BRACKET_RIGHT><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final IntArrayType n) {
    // f0 -> <INT_TYPE>
    final NodeToken n0 = n.f0;
    n0.accept(this);
    // f1 -> <BRACKET_LEFT>
    final NodeToken n1 = n.f1;
    n1.accept(this);
    // f2 -> <BRACKET_RIGHT>
    final NodeToken n2 = n.f2;
    n2.accept(this);
  }

  /**
   * Visits a {@link Identifier} node, whose child is the following :
   * <p>
   * f0 -> <IDENTIFIER><br>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Identifier n) {
    // f0 -> <IDENTIFIER>
    final NodeToken n0 = n.f0;
    n0.accept(this);
  }

  /**
   * Visits a {@link Empty} node, whose children are the following :
   * <p>
   *
   * @param n - the node to visit
   */
  @Override
  public void visit(final Empty n) {
  }

@Override
public void visit(Operator operator) {
	operator.accept(this);
}

}
