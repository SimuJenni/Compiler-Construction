package ch.unibe.iam.scg.minijava.typechecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.ConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.ObjectCreationExpression;
import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class ShuntingYard extends VoidVisitorAdapter {
	private Stack<Operator> stack = new Stack<Operator>();
    List<INode> output = new ArrayList<INode>();

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
          pushOnStack(new Operator((UnaryOperator) seq1));          
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
          pushOnStack(Operator.makeLeftParanthesis());
          // #1 Expression()
          final INode seq6 = seq4.elementAt(1);
          seq6.accept(this);
          // #2 <PARENTHESIS_RIGHT>
          pushOnStack(Operator.makeRightParanthesis());
          // #3 ExpressionPrime()
          final INode seq8 = seq4.elementAt(3);
          seq8.accept(this);
          break;
        case 3:
          // %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
          final NodeSequence seq9 = (NodeSequence) ich;
          // #0 <INTEGER_LITERAL>
          final INode seq10 = seq9.elementAt(0);
          output.add(seq10);
          // #1 ExpressionPrime()
          final INode seq11 = seq9.elementAt(1);
          seq11.accept(this);
          break;
        case 4:
          // %4 #0 <TRUE> #1 ExpressionPrime()
          final NodeSequence seq12 = (NodeSequence) ich;
          // #0 <TRUE>
          final INode seq13 = seq12.elementAt(0);
          output.add(seq13);
          // #1 ExpressionPrime()
          final INode seq14 = seq12.elementAt(1);
          seq14.accept(this);
          break;
        case 5:
          // %5 #0 <FALSE> #1 ExpressionPrime()
          final NodeSequence seq15 = (NodeSequence) ich;
          // #0 <FALSE>
          final INode seq16 = seq15.elementAt(0);
          output.add(seq16);
          // #1 ExpressionPrime()
          final INode seq17 = seq15.elementAt(1);
          seq17.accept(this);
          break;
        case 6:
          // %6 #0 <THIS> #1 ExpressionPrime()
          final NodeSequence seq18 = (NodeSequence) ich;
          // #0 <THIS>
          final INode seq19 = seq18.elementAt(0);
          output.add(seq19);
          // #1 ExpressionPrime()
          final INode seq20 = seq18.elementAt(1);
          seq20.accept(this);
          break;
        case 7:
          // %7 #0 Identifier() #1 ExpressionPrime()
          final NodeSequence seq21 = (NodeSequence) ich;
          // #0 Identifier()
          final INode seq22 = seq21.elementAt(0);
          output.add(seq22);
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
          pushOnStack(new Operator((BinaryOperator) seq1));          
          // #1 Expression()
          final INode seq2 = seq.elementAt(1);
          seq2.accept(this);
          // #2 ExpressionPrime()
          final INode seq3 = seq.elementAt(2);
          seq3.accept(this);
          break;
        case 1:
          // TODO Stupid brackets: need int[] type... 
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
          output.add(seq5);
          // #1 <BRACKET_LEFT>
          pushOnStack(Operator.makeLeftBracket());
          // #2 Expression()
          final INode seq7 = seq4.elementAt(2);
          seq7.accept(this);
          // #3 <BRACKET_RIGHT>
          pushOnStack(Operator.makeRightBracket());
          break;
        default:
          // should not occur !!!
          break;
      }
    }
    
    private void pushOnStack(Operator operator) {
    	
		while(!operator.isLeftParanthesis()&&!stack.empty()){
			Operator top=stack.peek();
			if(top.isLeftParanthesis()){
				stack.pop();
				break;
			}
			if(operator.isRightParanthesis()||top.hasHigherPrecedence(operator)){
				output.add(stack.pop());
			}
			else
				break;
		}
		stack.push(operator);
	}

	public void flushStack() {
		while(!stack.empty()){	
			output.add(stack.pop());
		}
	}
}

