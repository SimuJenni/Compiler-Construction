/* Generated by JTB 1.4.9 */
package ch.unibe.iam.scg.javacc.visitor;

import ch.unibe.iam.scg.javacc.syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first order.<br>
 * In your "RetArgu" visitors extend this class and override part or all of these methods.
 *
 * @param <R> - The user return information type
 * @param <A> - The user argument type
 */
public class DepthFirstRetArguVisitor<R, A> implements IRetArguVisitor<R, A> {


  /*
   * Base nodes classes visit methods (to be overridden if necessary)
   */

  /**
   * Visits a {@link NodeChoice} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeChoice n, final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    final R nRes = n.choice.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link NodeList} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeList n, final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    R nRes = null;
    for (final Iterator<INode> e = n.elements(); e.hasNext();) {
      @SuppressWarnings("unused")
      final R sRes = e.next().accept(this, argu);
    }
    return nRes;
  }

  /**
   * Visits a {@link NodeListOptional} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeListOptional n, final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    if (n.present()) {
      R nRes = null;
      for (final Iterator<INode> e = n.elements(); e.hasNext();) {
        @SuppressWarnings("unused")
        R sRes = e.next().accept(this, argu);
        }
      return nRes;
    } else
      return null;
  }

  /**
   * Visits a {@link NodeOptional} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeOptional n, final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    if (n.present()) {
      final R nRes = n.node.accept(this, argu);
      return nRes;
    } else
      return null;
  }

  /**
   * Visits a {@link NodeSequence} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeSequence n, final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    R nRes = null;
    for (final Iterator<INode> e = n.elements(); e.hasNext();) {
      @SuppressWarnings("unused")
      R subRet = e.next().accept(this, argu);
    }
    return nRes;
  }

  /**
   * Visits a {@link NodeTCF} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeTCF n, @SuppressWarnings("unused") final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    R nRes = null;
    @SuppressWarnings("unused")
    final String tkIm = n.tokenImage;
    return nRes;
  }

  /**
   * Visits a {@link NodeToken} node.
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final NodeToken n, @SuppressWarnings("unused") final A argu) {
    /* You have to adapt which data is returned (result variables below are just examples) */
    R nRes = null;
    @SuppressWarnings("unused")
    final String tkIm = n.tokenImage;
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Goal n, final A argu) {
    R nRes = null;
    // f0 -> MainClass()
    final MainClass n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> ( ClassDeclaration() )*
    final NodeListOptional n1 = n.f1;
    if (n1.present()) {
      for (int i = 0; i < n1.size(); i++) {
        final INode nloeai = n1.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f2 -> <EOF>
    final NodeToken n2 = n.f2;
    nRes = n2.accept(this, argu);
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final MainClass n, final A argu) {
    R nRes = null;
    // f0 -> <CLASS>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> <BRACE_LEFT>
    final NodeToken n2 = n.f2;
    nRes = n2.accept(this, argu);
    // f3 -> <PUBLIC_MODIFIER>
    final NodeToken n3 = n.f3;
    nRes = n3.accept(this, argu);
    // f4 -> <STATIC_MODIFIER>
    final NodeToken n4 = n.f4;
    nRes = n4.accept(this, argu);
    // f5 -> <VOID_TYPE>
    final NodeToken n5 = n.f5;
    nRes = n5.accept(this, argu);
    // f6 -> <MAIN_METHOD_NAME>
    final NodeToken n6 = n.f6;
    nRes = n6.accept(this, argu);
    // f7 -> <PARENTHESIS_LEFT>
    final NodeToken n7 = n.f7;
    nRes = n7.accept(this, argu);
    // f8 -> <STRING_TYPE>
    final NodeToken n8 = n.f8;
    nRes = n8.accept(this, argu);
    // f9 -> <BRACKET_LEFT>
    final NodeToken n9 = n.f9;
    nRes = n9.accept(this, argu);
    // f10 -> <BRACKET_RIGHT>
    final NodeToken n10 = n.f10;
    nRes = n10.accept(this, argu);
    // f11 -> Identifier()
    final Identifier n11 = n.f11;
    nRes = n11.accept(this, argu);
    // f12 -> <PARENTHESIS_RIGHT>
    final NodeToken n12 = n.f12;
    nRes = n12.accept(this, argu);
    // f13 -> <BRACE_LEFT>
    final NodeToken n13 = n.f13;
    nRes = n13.accept(this, argu);
    // f14 -> ( Statement() )?
    final NodeOptional n14 = n.f14;
    if (n14.present()) {
      nRes = n14.accept(this, argu);
    }
    // f15 -> <BRACE_RIGHT>
    final NodeToken n15 = n.f15;
    nRes = n15.accept(this, argu);
    // f16 -> <BRACE_RIGHT>
    final NodeToken n16 = n.f16;
    nRes = n16.accept(this, argu);
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final ClassDeclaration n, final A argu) {
    R nRes = null;
    // f0 -> <CLASS>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> ( #0 <EXTENDS> #1 Identifier() )?
    final NodeOptional n2 = n.f2;
    if (n2.present()) {
      final NodeSequence seq = (NodeSequence) n2.node;
      // #0 <EXTENDS>
      final INode seq1 = seq.elementAt(0);
      nRes = seq1.accept(this, argu);
      // #1 Identifier()
      final INode seq2 = seq.elementAt(1);
      nRes = seq2.accept(this, argu);
    }
    // f3 -> <BRACE_LEFT>
    final NodeToken n3 = n.f3;
    nRes = n3.accept(this, argu);
    // f4 -> ( VarDeclaration() )*
    final NodeListOptional n4 = n.f4;
    if (n4.present()) {
      for (int i = 0; i < n4.size(); i++) {
        final INode nloeai = n4.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f5 -> ( MethodDeclaration() )*
    final NodeListOptional n5 = n.f5;
    if (n5.present()) {
      for (int i = 0; i < n5.size(); i++) {
        final INode nloeai = n5.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f6 -> <BRACE_RIGHT>
    final NodeToken n6 = n.f6;
    nRes = n6.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link VarDeclaration} node, whose children are the following :
   * <p>
   * f0 -> Type()<br>
   * f1 -> Identifier()<br>
   * f2 -> <SEMICOLON><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final VarDeclaration n, final A argu) {
    R nRes = null;
    // f0 -> Type()
    final Type n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> <SEMICOLON>
    final NodeToken n2 = n.f2;
    nRes = n2.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link MethodDeclaration} node, whose children are the following :
   * <p>
   * f0 -> <PUBLIC_MODIFIER><br>
   * f1 -> Type()<br>
   * f2 -> Identifier()<br>
   * f3 -> <PARENTHESIS_LEFT><br>
   * f4 -> ( #0 Type() #1 Identifier()<br>
   * .. .. . #2 ( $0 <COMMA> $1 Type() $2 Identifier() )* )?<br>
   * f5 -> <PARENTHESIS_RIGHT><br>
   * f6 -> <BRACE_LEFT><br>
   * f7 -> ( VarDeclaration() )*<br>
   * f8 -> ( Statement() )*<br>
   * f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?<br>
   * f10 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final MethodDeclaration n, final A argu) {
    R nRes = null;
    // f0 -> <PUBLIC_MODIFIER>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> Type()
    final Type n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> Identifier()
    final Identifier n2 = n.f2;
    nRes = n2.accept(this, argu);
    // f3 -> <PARENTHESIS_LEFT>
    final NodeToken n3 = n.f3;
    nRes = n3.accept(this, argu);
    // f4 -> ( #0 Type() #1 Identifier()
    // .. .. . #2 ( $0 <COMMA> $1 Type() $2 Identifier() )* )?
    final NodeOptional n4 = n.f4;
    if (n4.present()) {
      final NodeSequence seq = (NodeSequence) n4.node;
      // #0 Type()
      final INode seq1 = seq.elementAt(0);
      nRes = seq1.accept(this, argu);
      // #1 Identifier()
      final INode seq2 = seq.elementAt(1);
      nRes = seq2.accept(this, argu);
      // #2 ( $0 <COMMA> $1 Type() $2 Identifier() )*
      final INode seq3 = seq.elementAt(2);
      final NodeListOptional nlo = (NodeListOptional) seq3;
      if (nlo.present()) {
        for (int i = 0; i < nlo.size(); i++) {
          final INode nloeai = nlo.elementAt(i);
          final NodeSequence seq4 = (NodeSequence) nloeai;
          // $0 <COMMA>
          final INode seq5 = seq4.elementAt(0);
          nRes = seq5.accept(this, argu);
          // $1 Type()
          final INode seq6 = seq4.elementAt(1);
          nRes = seq6.accept(this, argu);
          // $2 Identifier()
          final INode seq7 = seq4.elementAt(2);
          nRes = seq7.accept(this, argu);
        }
      }
    }
    // f5 -> <PARENTHESIS_RIGHT>
    final NodeToken n5 = n.f5;
    nRes = n5.accept(this, argu);
    // f6 -> <BRACE_LEFT>
    final NodeToken n6 = n.f6;
    nRes = n6.accept(this, argu);
    // f7 -> ( VarDeclaration() )*
    final NodeListOptional n7 = n.f7;
    if (n7.present()) {
      for (int i = 0; i < n7.size(); i++) {
        final INode nloeai = n7.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f8 -> ( Statement() )*
    final NodeListOptional n8 = n.f8;
    if (n8.present()) {
      for (int i = 0; i < n8.size(); i++) {
        final INode nloeai = n8.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
    final NodeOptional n9 = n.f9;
    if (n9.present()) {
      final NodeSequence seq8 = (NodeSequence) n9.node;
      // #0 <RETURN>
      final INode seq9 = seq8.elementAt(0);
      nRes = seq9.accept(this, argu);
      // #1 Expression()
      final INode seq10 = seq8.elementAt(1);
      nRes = seq10.accept(this, argu);
      // #2 <SEMICOLON>
      final INode seq11 = seq8.elementAt(2);
      nRes = seq11.accept(this, argu);
    }
    // f10 -> <BRACE_RIGHT>
    final NodeToken n10 = n.f10;
    nRes = n10.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link Type} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT><br>
   * .. .. | %1 <INT_TYPE><br>
   * .. .. | %2 BooleanType()<br>
   * .. .. | %3 <VOID_TYPE><br>
   * .. .. | %4 Identifier()<br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Type n, final A argu) {
    R nRes = null;
    // f0 -> . %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT>
    // .. .. | %1 <INT_TYPE>
    // .. .. | %2 BooleanType()
    // .. .. | %3 <VOID_TYPE>
    // .. .. | %4 Identifier()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT>
        final NodeSequence seq = (NodeSequence) ich;
        // #0 <INT_TYPE>
        final INode seq1 = seq.elementAt(0);
        nRes = seq1.accept(this, argu);
        // #1 <BRACKET_LEFT>
        final INode seq2 = seq.elementAt(1);
        nRes = seq2.accept(this, argu);
        // #2 <BRACKET_RIGHT>
        final INode seq3 = seq.elementAt(2);
        nRes = seq3.accept(this, argu);
        break;
      case 1:
        // %1 <INT_TYPE>
        nRes = ich.accept(this, argu);
        break;
      case 2:
        // %2 BooleanType()
        nRes = ich.accept(this, argu);
        break;
      case 3:
        // %3 <VOID_TYPE>
        nRes = ich.accept(this, argu);
        break;
      case 4:
        // %4 Identifier()
        nRes = ich.accept(this, argu);
        break;
      default:
        // should not occur !!!
        break;
    }
    return nRes;
  }

  /**
   * Visits a {@link Statement} node, whose child is the following :
   * <p>
   * f0 -> . %0 StatementList()<br>
   * .. .. | %1 If()<br>
   * .. .. | %2 WhileLoop()<br>
   * .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON><br>
   * .. .. | %4 #0 Identifier() #1 Assignment() #2 Expression() #3 <SEMICOLON><br>
   * .. .. | %5 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT> #4 Assignment() #5 Expression() #6 <SEMICOLON><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Statement n, final A argu) {
    R nRes = null;
    // f0 -> . %0 StatementList()
    // .. .. | %1 If()
    // .. .. | %2 WhileLoop()
    // .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
    // .. .. | %4 #0 Identifier() #1 Assignment() #2 Expression() #3 <SEMICOLON>
    // .. .. | %5 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT> #4 Assignment() #5 Expression() #6 <SEMICOLON>
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 StatementList()
        nRes = ich.accept(this, argu);
        break;
      case 1:
        // %1 If()
        nRes = ich.accept(this, argu);
        break;
      case 2:
        // %2 WhileLoop()
        nRes = ich.accept(this, argu);
        break;
      case 3:
        // %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
        final NodeSequence seq = (NodeSequence) ich;
        // #0 <PRINT_METHOD>
        final INode seq1 = seq.elementAt(0);
        nRes = seq1.accept(this, argu);
        // #1 <PARENTHESIS_LEFT>
        final INode seq2 = seq.elementAt(1);
        nRes = seq2.accept(this, argu);
        // #2 Expression()
        final INode seq3 = seq.elementAt(2);
        nRes = seq3.accept(this, argu);
        // #3 <PARENTHESIS_RIGHT>
        final INode seq4 = seq.elementAt(3);
        nRes = seq4.accept(this, argu);
        // #4 <SEMICOLON>
        final INode seq5 = seq.elementAt(4);
        nRes = seq5.accept(this, argu);
        break;
      case 4:
        // %4 #0 Identifier() #1 Assignment() #2 Expression() #3 <SEMICOLON>
        final NodeSequence seq6 = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq7 = seq6.elementAt(0);
        nRes = seq7.accept(this, argu);
        // #1 Assignment()
        final INode seq8 = seq6.elementAt(1);
        nRes = seq8.accept(this, argu);
        // #2 Expression()
        final INode seq9 = seq6.elementAt(2);
        nRes = seq9.accept(this, argu);
        // #3 <SEMICOLON>
        final INode seq10 = seq6.elementAt(3);
        nRes = seq10.accept(this, argu);
        break;
      case 5:
        // %5 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT> #4 Assignment() #5 Expression() #6 <SEMICOLON>
        final NodeSequence seq11 = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq12 = seq11.elementAt(0);
        nRes = seq12.accept(this, argu);
        // #1 <BRACKET_LEFT>
        final INode seq13 = seq11.elementAt(1);
        nRes = seq13.accept(this, argu);
        // #2 Expression()
        final INode seq14 = seq11.elementAt(2);
        nRes = seq14.accept(this, argu);
        // #3 <BRACKET_RIGHT>
        final INode seq15 = seq11.elementAt(3);
        nRes = seq15.accept(this, argu);
        // #4 Assignment()
        final INode seq16 = seq11.elementAt(4);
        nRes = seq16.accept(this, argu);
        // #5 Expression()
        final INode seq17 = seq11.elementAt(5);
        nRes = seq17.accept(this, argu);
        // #6 <SEMICOLON>
        final INode seq18 = seq11.elementAt(6);
        nRes = seq18.accept(this, argu);
        break;
      default:
        // should not occur !!!
        break;
    }
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final WhileLoop n, final A argu) {
    R nRes = null;
    // f0 -> <WHILE>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> <PARENTHESIS_LEFT>
    final NodeToken n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> Expression()
    final Expression n2 = n.f2;
    nRes = n2.accept(this, argu);
    // f3 -> <PARENTHESIS_RIGHT>
    final NodeToken n3 = n.f3;
    nRes = n3.accept(this, argu);
    // f4 -> Statement()
    final Statement n4 = n.f4;
    nRes = n4.accept(this, argu);
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final If n, final A argu) {
    R nRes = null;
    // f0 -> <IF>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> <PARENTHESIS_LEFT>
    final NodeToken n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> Expression()
    final Expression n2 = n.f2;
    nRes = n2.accept(this, argu);
    // f3 -> <PARENTHESIS_RIGHT>
    final NodeToken n3 = n.f3;
    nRes = n3.accept(this, argu);
    // f4 -> Statement()
    final Statement n4 = n.f4;
    nRes = n4.accept(this, argu);
    // f5 -> <ELSE>
    final NodeToken n5 = n.f5;
    nRes = n5.accept(this, argu);
    // f6 -> Statement()
    final Statement n6 = n.f6;
    nRes = n6.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link StatementList} node, whose children are the following :
   * <p>
   * f0 -> <BRACE_LEFT><br>
   * f1 -> ( Statement() )*<br>
   * f2 -> <BRACE_RIGHT><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final StatementList n, final A argu) {
    R nRes = null;
    // f0 -> <BRACE_LEFT>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> ( Statement() )*
    final NodeListOptional n1 = n.f1;
    if (n1.present()) {
      for (int i = 0; i < n1.size(); i++) {
        final INode nloeai = n1.elementAt(i);
        nRes = nloeai.accept(this, argu);
      }
    }
    // f2 -> <BRACE_RIGHT>
    final NodeToken n2 = n.f2;
    nRes = n2.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link Expression} node, whose child is the following :
   * <p>
   * f0 -> . %0 #0 New() #1 <INT_TYPE> #2 <BRACKET_LEFT> #3 Expression() #4 <BRACKET_RIGHT> #5 ExpressionPrime()<br>
   * .. .. | %1 #0 New() #1 Identifier() #2 <PARENTHESIS_LEFT> #3 <PARENTHESIS_RIGHT> #4 ExpressionPrime()<br>
   * .. .. | %2 #0 <UNARY_OPERATOR> #1 Expression() #2 ExpressionPrime()<br>
   * .. .. | %3 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()<br>
   * .. .. | %4 #0 <INTEGER_LITERAL> #1 ExpressionPrime()<br>
   * .. .. | %5 #0 <TRUE> #1 ExpressionPrime()<br>
   * .. .. | %6 #0 <FALSE> #1 ExpressionPrime()<br>
   * .. .. | %7 #0 <THIS> #1 ExpressionPrime()<br>
   * .. .. | %8 #0 Identifier() #1 ExpressionPrime()<br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Expression n, final A argu) {
    R nRes = null;
    // f0 -> . %0 #0 New() #1 <INT_TYPE> #2 <BRACKET_LEFT> #3 Expression() #4 <BRACKET_RIGHT> #5 ExpressionPrime()
    // .. .. | %1 #0 New() #1 Identifier() #2 <PARENTHESIS_LEFT> #3 <PARENTHESIS_RIGHT> #4 ExpressionPrime()
    // .. .. | %2 #0 <UNARY_OPERATOR> #1 Expression() #2 ExpressionPrime()
    // .. .. | %3 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()
    // .. .. | %4 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
    // .. .. | %5 #0 <TRUE> #1 ExpressionPrime()
    // .. .. | %6 #0 <FALSE> #1 ExpressionPrime()
    // .. .. | %7 #0 <THIS> #1 ExpressionPrime()
    // .. .. | %8 #0 Identifier() #1 ExpressionPrime()
    final NodeChoice nch = n.f0;
    final INode ich = nch.choice;
    switch (nch.which) {
      case 0:
        // %0 #0 New() #1 <INT_TYPE> #2 <BRACKET_LEFT> #3 Expression() #4 <BRACKET_RIGHT> #5 ExpressionPrime()
        final NodeSequence seq = (NodeSequence) ich;
        // #0 New()
        final INode seq1 = seq.elementAt(0);
        nRes = seq1.accept(this, argu);
        // #1 <INT_TYPE>
        final INode seq2 = seq.elementAt(1);
        nRes = seq2.accept(this, argu);
        // #2 <BRACKET_LEFT>
        final INode seq3 = seq.elementAt(2);
        nRes = seq3.accept(this, argu);
        // #3 Expression()
        final INode seq4 = seq.elementAt(3);
        nRes = seq4.accept(this, argu);
        // #4 <BRACKET_RIGHT>
        final INode seq5 = seq.elementAt(4);
        nRes = seq5.accept(this, argu);
        // #5 ExpressionPrime()
        final INode seq6 = seq.elementAt(5);
        nRes = seq6.accept(this, argu);
        break;
      case 1:
        // %1 #0 New() #1 Identifier() #2 <PARENTHESIS_LEFT> #3 <PARENTHESIS_RIGHT> #4 ExpressionPrime()
        final NodeSequence seq7 = (NodeSequence) ich;
        // #0 New()
        final INode seq8 = seq7.elementAt(0);
        nRes = seq8.accept(this, argu);
        // #1 Identifier()
        final INode seq9 = seq7.elementAt(1);
        nRes = seq9.accept(this, argu);
        // #2 <PARENTHESIS_LEFT>
        final INode seq10 = seq7.elementAt(2);
        nRes = seq10.accept(this, argu);
        // #3 <PARENTHESIS_RIGHT>
        final INode seq11 = seq7.elementAt(3);
        nRes = seq11.accept(this, argu);
        // #4 ExpressionPrime()
        final INode seq12 = seq7.elementAt(4);
        nRes = seq12.accept(this, argu);
        break;
      case 2:
        // %2 #0 <UNARY_OPERATOR> #1 Expression() #2 ExpressionPrime()
        final NodeSequence seq13 = (NodeSequence) ich;
        // #0 <UNARY_OPERATOR>
        final INode seq14 = seq13.elementAt(0);
        nRes = seq14.accept(this, argu);
        // #1 Expression()
        final INode seq15 = seq13.elementAt(1);
        nRes = seq15.accept(this, argu);
        // #2 ExpressionPrime()
        final INode seq16 = seq13.elementAt(2);
        nRes = seq16.accept(this, argu);
        break;
      case 3:
        // %3 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()
        final NodeSequence seq17 = (NodeSequence) ich;
        // #0 <PARENTHESIS_LEFT>
        final INode seq18 = seq17.elementAt(0);
        nRes = seq18.accept(this, argu);
        // #1 Expression()
        final INode seq19 = seq17.elementAt(1);
        nRes = seq19.accept(this, argu);
        // #2 <PARENTHESIS_RIGHT>
        final INode seq20 = seq17.elementAt(2);
        nRes = seq20.accept(this, argu);
        // #3 ExpressionPrime()
        final INode seq21 = seq17.elementAt(3);
        nRes = seq21.accept(this, argu);
        break;
      case 4:
        // %4 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
        final NodeSequence seq22 = (NodeSequence) ich;
        // #0 <INTEGER_LITERAL>
        final INode seq23 = seq22.elementAt(0);
        nRes = seq23.accept(this, argu);
        // #1 ExpressionPrime()
        final INode seq24 = seq22.elementAt(1);
        nRes = seq24.accept(this, argu);
        break;
      case 5:
        // %5 #0 <TRUE> #1 ExpressionPrime()
        final NodeSequence seq25 = (NodeSequence) ich;
        // #0 <TRUE>
        final INode seq26 = seq25.elementAt(0);
        nRes = seq26.accept(this, argu);
        // #1 ExpressionPrime()
        final INode seq27 = seq25.elementAt(1);
        nRes = seq27.accept(this, argu);
        break;
      case 6:
        // %6 #0 <FALSE> #1 ExpressionPrime()
        final NodeSequence seq28 = (NodeSequence) ich;
        // #0 <FALSE>
        final INode seq29 = seq28.elementAt(0);
        nRes = seq29.accept(this, argu);
        // #1 ExpressionPrime()
        final INode seq30 = seq28.elementAt(1);
        nRes = seq30.accept(this, argu);
        break;
      case 7:
        // %7 #0 <THIS> #1 ExpressionPrime()
        final NodeSequence seq31 = (NodeSequence) ich;
        // #0 <THIS>
        final INode seq32 = seq31.elementAt(0);
        nRes = seq32.accept(this, argu);
        // #1 ExpressionPrime()
        final INode seq33 = seq31.elementAt(1);
        nRes = seq33.accept(this, argu);
        break;
      case 8:
        // %8 #0 Identifier() #1 ExpressionPrime()
        final NodeSequence seq34 = (NodeSequence) ich;
        // #0 Identifier()
        final INode seq35 = seq34.elementAt(0);
        nRes = seq35.accept(this, argu);
        // #1 ExpressionPrime()
        final INode seq36 = seq34.elementAt(1);
        nRes = seq36.accept(this, argu);
        break;
      default:
        // should not occur !!!
        break;
    }
    return nRes;
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
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final ExpressionPrime n, final A argu) {
    R nRes = null;
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
        nRes = seq1.accept(this, argu);
        // #1 Expression()
        final INode seq2 = seq.elementAt(1);
        nRes = seq2.accept(this, argu);
        // #2 ExpressionPrime()
        final INode seq3 = seq.elementAt(2);
        nRes = seq3.accept(this, argu);
        break;
      case 1:
        // %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()
        final NodeSequence seq4 = (NodeSequence) ich;
        // #0 <BRACKET_LEFT>
        final INode seq5 = seq4.elementAt(0);
        nRes = seq5.accept(this, argu);
        // #1 Expression()
        final INode seq6 = seq4.elementAt(1);
        nRes = seq6.accept(this, argu);
        // #2 <BRACKET_RIGHT>
        final INode seq7 = seq4.elementAt(2);
        nRes = seq7.accept(this, argu);
        // #3 ExpressionPrime()
        final INode seq8 = seq4.elementAt(3);
        nRes = seq8.accept(this, argu);
        break;
      case 2:
        // %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()
        final NodeSequence seq9 = (NodeSequence) ich;
        // #0 <DOT>
        final INode seq10 = seq9.elementAt(0);
        nRes = seq10.accept(this, argu);
        // #1 <LENGTH_FIELD_NAME>
        final INode seq11 = seq9.elementAt(1);
        nRes = seq11.accept(this, argu);
        // #2 ExpressionPrime()
        final INode seq12 = seq9.elementAt(2);
        nRes = seq12.accept(this, argu);
        break;
      case 3:
        // %3 MethodCall()
        nRes = ich.accept(this, argu);
        break;
      case 4:
        // %4 Empty()
        nRes = ich.accept(this, argu);
        break;
      default:
        // should not occur !!!
        break;
    }
    return nRes;
  }

  /**
   * Visits a {@link MethodCall} node, whose children are the following :
   * <p>
   * f0 -> <DOT><br>
   * f1 -> Identifier()<br>
   * f2 -> <PARENTHESIS_LEFT><br>
   * f3 -> ( #0 Expression()<br>
   * .. .. . #1 ( $0 <COMMA> $1 Expression() )* )?<br>
   * f4 -> <PARENTHESIS_RIGHT><br>
   * f5 -> ExpressionPrime()<br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final MethodCall n, final A argu) {
    R nRes = null;
    // f0 -> <DOT>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    // f1 -> Identifier()
    final Identifier n1 = n.f1;
    nRes = n1.accept(this, argu);
    // f2 -> <PARENTHESIS_LEFT>
    final NodeToken n2 = n.f2;
    nRes = n2.accept(this, argu);
    // f3 -> ( #0 Expression()
    // .. .. . #1 ( $0 <COMMA> $1 Expression() )* )?
    final NodeOptional n3 = n.f3;
    if (n3.present()) {
      final NodeSequence seq = (NodeSequence) n3.node;
      // #0 Expression()
      final INode seq1 = seq.elementAt(0);
      nRes = seq1.accept(this, argu);
      // #1 ( $0 <COMMA> $1 Expression() )*
      final INode seq2 = seq.elementAt(1);
      final NodeListOptional nlo = (NodeListOptional) seq2;
      if (nlo.present()) {
        for (int i = 0; i < nlo.size(); i++) {
          final INode nloeai = nlo.elementAt(i);
          final NodeSequence seq3 = (NodeSequence) nloeai;
          // $0 <COMMA>
          final INode seq4 = seq3.elementAt(0);
          nRes = seq4.accept(this, argu);
          // $1 Expression()
          final INode seq5 = seq3.elementAt(1);
          nRes = seq5.accept(this, argu);
        }
      }
    }
    // f4 -> <PARENTHESIS_RIGHT>
    final NodeToken n4 = n.f4;
    nRes = n4.accept(this, argu);
    // f5 -> ExpressionPrime()
    final ExpressionPrime n5 = n.f5;
    nRes = n5.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link BinaryOperator} node, whose child is the following :
   * <p>
   * f0 -> <BINARY_OPERATOR><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final BinaryOperator n, final A argu) {
    R nRes = null;
    // f0 -> <BINARY_OPERATOR>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link New} node, whose child is the following :
   * <p>
   * f0 -> <NEW><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final New n, final A argu) {
    R nRes = null;
    // f0 -> <NEW>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link Assignment} node, whose child is the following :
   * <p>
   * f0 -> <EQUALS_SIGN><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Assignment n, final A argu) {
    R nRes = null;
    // f0 -> <EQUALS_SIGN>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link Empty} node, whose children are the following :
   * <p>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Empty n, final A argu) {
    R nRes = null;
    return nRes;
  }

  /**
   * Visits a {@link BooleanType} node, whose child is the following :
   * <p>
   * f0 -> <BOOLEAN_TYPE><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final BooleanType n, final A argu) {
    R nRes = null;
    // f0 -> <BOOLEAN_TYPE>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    return nRes;
  }

  /**
   * Visits a {@link Identifier} node, whose child is the following :
   * <p>
   * f0 -> <IDENTIFIER><br>
   *
   * @param n - the node to visit
   * @param argu - the user argument
   * @return the user return information
   */
  @Override
  public R visit(final Identifier n, final A argu) {
    R nRes = null;
    // f0 -> <IDENTIFIER>
    final NodeToken n0 = n.f0;
    nRes = n0.accept(this, argu);
    return nRes;
  }

}