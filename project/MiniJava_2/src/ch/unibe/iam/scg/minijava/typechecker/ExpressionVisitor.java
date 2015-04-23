package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.MiniJavaImplConstants;
import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class ExpressionVisitor extends DepthFirstVoidVisitor implements MiniJavaImplConstants {
	private SymbolTable table;
	private String expectedType;
	private boolean valid;
	private Stack<String> typeStack = new Stack<String>();

	public ExpressionVisitor(SymbolTable table) {
		this.table=table;
		valid=true;
	}
	
	public boolean check(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return valid;
	}
	

	  @Override
	  public void visit(final Expression n) {
		  ShuntingYard sy = new ShuntingYard();
	      n.accept(sy);
	      sy.flushStack();
	        
	      for (INode node : sy.output) {
	          node.accept(this);
	      }
	        
	  }
	  
	  /**
	   * Visits a {@link NodeToken} node.
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final NodeToken n) {
	    String tokenType=tokenImage[n.kind];
	    String type=null;
	    if(tokenType.equals("<INTEGER_LITERAL>"))
	    	type=Types.INT.getName();
	    if(tokenType.equals("\"true\""))
	    	type=Types.BOOLEAN.getName();
	    if(tokenType.equals("\"false\""))
	    	type=Types.BOOLEAN.getName();

	    typeStack.push(type);
	    String tkIm = n.tokenImage;
	  }
	  
	  public void visit(Operator operator){
		  if(operator.isUnary()){
			  String argType=operator.getArgType();
			  checkSameType(argType, typeStack.pop());
			  typeStack.push(operator.getReturnType());

		  }
		  if(operator.isBinary()){
			  String argType=operator.getArgType();
			  checkSameType(argType, typeStack.pop());
			  checkSameType(argType, typeStack.pop()); 
			  typeStack.push(operator.getReturnType());
			  
		  }
		  
	  }
	
	  private String checkSameType(String type1, String type2) {
		if(type1==null)
			return type2;
		if(type2==null)
			return type1;
		if(type1.equals(type2))
			return type1;
		else{
			valid=false;
			return null;
		}
	}




}
