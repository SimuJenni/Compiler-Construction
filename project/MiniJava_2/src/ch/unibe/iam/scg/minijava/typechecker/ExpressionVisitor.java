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
	        
//	    final NodeChoice nch = n.f0;
//	    final INode ich = nch.choice;
//	    switch (nch.which) {
//	      case 0:
//	        // %0 ObjectCreationExpression()
//	        ich.accept(this);
//	        break;
//	      case 1:
//	        // %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()
//	        final NodeSequence seq = (NodeSequence) ich;
//	        // #1 Expression()
//	        final INode seq2 = seq.elementAt(1);
//	        // #2 ExpressionPrime()
//	        final INode seq3 = seq.elementAt(2);
//	        String type=checkSameType(seq2.accept(this),seq3.accept(this));
//	        return checkSameType(Types.BOOLEAN.getName(),type);
//	      case 2:
//	        // %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT> #3 ExpressionPrime()
//	        final NodeSequence seq4 = (NodeSequence) ich;
//	        // #1 Expression()
//	        final INode seq6 = seq4.elementAt(1);
//	        // #3 ExpressionPrime()
//	        final INode seq8 = seq4.elementAt(3);
//	        return checkSameType(seq6.accept(this),seq8.accept(this));
//	      case 3:
//	        // %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
//	        final NodeSequence seq9 = (NodeSequence) ich;
//	        // #1 ExpressionPrime()
//	        final INode seq11 = seq9.elementAt(1);
//	        expectedType=Types.INT.getName();
//	        
//	        return checkSameType(Types.INT.getName(),seq11.accept(this));
//		case 4:
//	        // %4 #0 <TRUE> #1 ExpressionPrime()
//	        final NodeSequence seq12 = (NodeSequence) ich;
//	        // #1 ExpressionPrime()
//	        final INode seq14 = seq12.elementAt(1);
//	        return checkSameType(Types.BOOLEAN.getName(),seq14.accept(this));
//	      case 5:
//	        // %5 #0 <FALSE> #1 ExpressionPrime()
//	        final NodeSequence seq15 = (NodeSequence) ich;
//	        // #1 ExpressionPrime()
//	        final INode seq17 = seq15.elementAt(1);
//	        return checkSameType(Types.BOOLEAN.getName(),seq17.accept(this));
//	      case 6:
//	        // %6 #0 <THIS> #1 ExpressionPrime()
//	        final NodeSequence seq18 = (NodeSequence) ich;
//	        // #0 <THIS>
//	        final INode seq19 = seq18.elementAt(0);
//	        seq19.accept(this);
//	        // #1 ExpressionPrime()
//	        final INode seq20 = seq18.elementAt(1);
//	        seq20.accept(this);
//	        break;
//	      case 7:
//	        // %7 #0 Identifier() #1 ExpressionPrime()
//	        final NodeSequence seq21 = (NodeSequence) ich;
//	        // #0 Identifier()
//	        final INode seq22 = seq21.elementAt(0);
//	        seq22.accept(this);
//	        // #1 ExpressionPrime()
//	        final INode seq23 = seq21.elementAt(1);
//	        seq23.accept(this);
//	        break;
//	      default:
//	        // should not occur !!!
//	        break;
//	    }
	  }
	  

//	/**
//	   * Visits a {@link ExpressionPrime} node, whose child is the following :
//	   * <p>
//	   * f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()<br>
//	   * .. .. | %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()<br>
//	   * .. .. | %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()<br>
//	   * .. .. | %3 MethodCall()<br>
//	   * .. .. | %4 Empty()<br>
//	   *
//	   * @param n - the node to visit
//	   */
//	  @Override
//	  public void visit(final ExpressionPrime n) {
//	    final NodeChoice nch = n.f0;
//	    final INode ich = nch.choice;
//	    switch (nch.which) {
//	      case 0:
//	        // %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()
//	        final NodeSequence seq = (NodeSequence) ich;
//	        // #0 BinaryOperator()
//	        final INode seq1 = seq.elementAt(0);
//	        // #1 Expression()
//	        final INode seq2 = seq.elementAt(1);
//	        // #2 ExpressionPrime()
//	        final INode seq3 = seq.elementAt(2);
//	        String argType = seq1.accept(new OperatorArgVisitor());
//	        String returnType = seq1.accept(new OperatorRetVisitor());
//	        String type=checkSameType(seq2.accept(this), seq3.accept(this));
//	        checkSameType(type,argType);
//	        checkSameType(expectedType,argType);
//	        return returnType;
//
//	      case 1:
//	        // %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3 ExpressionPrime()
//	        final NodeSequence seq4 = (NodeSequence) ich;
//	        // #0 <BRACKET_LEFT>
//	        final INode seq5 = seq4.elementAt(0);
//	        seq5.accept(this);
//	        // #1 Expression()
//	        final INode seq6 = seq4.elementAt(1);
//	        seq6.accept(this);
//	        // #2 <BRACKET_RIGHT>
//	        final INode seq7 = seq4.elementAt(2);
//	        seq7.accept(this);
//	        // #3 ExpressionPrime()
//	        final INode seq8 = seq4.elementAt(3);
//	        seq8.accept(this);
//	        break;
//	      case 2:
//	        // %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()
//	        final NodeSequence seq9 = (NodeSequence) ich;
//	        // #0 <DOT>
//	        final INode seq10 = seq9.elementAt(0);
//	        seq10.accept(this);
//	        // #1 <LENGTH_FIELD_NAME>
//	        final INode seq11 = seq9.elementAt(1);
//	        seq11.accept(this);
//	        // #2 ExpressionPrime()
//	        final INode seq12 = seq9.elementAt(2);
//	        seq12.accept(this);
//	        break;
//	      case 3:
//	        // %3 MethodCall()
//	        ich.accept(this);
//	        break;
//	      case 4:
//	        // %4 Empty()
//	        return null;
//	      default:
//	        // should not occur !!!
//	        break;
//	    }
//	    return null;
//	  }
	  
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
	    final String tkIm = n.tokenImage;
	  }
	  
	  public void visit(Operator operator){
		  if(operator.isUnary()){
			  
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
