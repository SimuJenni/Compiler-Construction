package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclarationList;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class MethodVisitor extends DepthFirstVoidVisitor {
	private SymbolTable table;
	private String returnType;
	private boolean valid;
	private ExpressionVisitor expressionVisitor;
	
	public MethodVisitor(SymbolTable table) {
		this.expressionVisitor=new ExpressionVisitor(table);
		this.table=table;
		valid=true;
	}

	public Boolean check(MethodDeclaration node) {
		// TODO Auto-generated method stub
		node.accept(this);
		return valid;
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
	      valid=expressionVisitor.check(seq2);
	      
	      // #2 <SEMICOLON>
	      final INode seq3 = seq.elementAt(2);
	      seq3.accept(this);
	    }
	    // f9 -> <BRACE_RIGHT>
	    final NodeToken n9 = n.f9;
	    n9.accept(this);
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


}
