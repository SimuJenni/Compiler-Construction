package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayAccess;
import ch.unibe.iam.scg.javacc.syntaxtree.ArrayLengthAccess;
import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.ObjectInstantiationExpression;
import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;

public class ShuntingYard extends DepthFirstVoidVisitor {

	protected Stack<IToken> stack;
	protected List<IToken> output;
	protected IScope scope;

	public ShuntingYard(IScope scope) {
		super();
		this.scope = scope;
		this.stack = new Stack<IToken>();
		this.output = new ArrayList<IToken>();
	}

	public List<IToken> extract(INode node) {
		node.accept(this);
		while (!stack.isEmpty()) {
			if (stack.peek().isParenthesis()) {
				throw new MismatchedParenthesisException();
			}
			output.add(stack.pop());
		}
		return this.output;
	}

	/**
	 * Visits a {@link Expression} node, whose child is the following :
	 * <p>
	 * f0 -> . %0 #0 ObjectInstantiationExpression() #1 ExpressionPrime()<br>
	 * .. .. | %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()<br>
	 * .. .. | %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT>
	 * #3 ExpressionPrime()<br>
	 * .. .. | %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()<br>
	 * .. .. | %4 #0 <TRUE> #1 ExpressionPrime()<br>
	 * .. .. | %5 #0 <FALSE> #1 ExpressionPrime()<br>
	 * .. .. | %6 #0 <THIS> #1 ExpressionPrime()<br>
	 * .. .. | %7 #0 Identifier() #1 ExpressionPrime()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final Expression n) {
		// f0 -> . %0 #0 ObjectInstantiationExpression() #1 ExpressionPrime()
		// .. .. | %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()
		// .. .. | %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2
		// <PARENTHESIS_RIGHT> #3 ExpressionPrime()
		// .. .. | %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
		// .. .. | %4 #0 <TRUE> #1 ExpressionPrime()
		// .. .. | %5 #0 <FALSE> #1 ExpressionPrime()
		// .. .. | %6 #0 <THIS> #1 ExpressionPrime()
		// .. .. | %7 #0 Identifier() #1 ExpressionPrime()
		final NodeChoice nch = n.f0;
		final INode ich = nch.choice;
		switch (nch.which) {
		case 0:
			// %0 #0 ObjectInstantiationExpression() #1 ExpressionPrime()
			final NodeSequence seq = (NodeSequence) ich;
			// #0 ObjectInstantiationExpression()
			final INode seq1 = seq.elementAt(0);
			this.stack.push(new ObjectInstantiationToken(
					(ObjectInstantiationExpression) seq1));
			seq1.accept(this);
			// #1 ExpressionPrime()
			final INode seq2 = seq.elementAt(1);
			seq2.accept(this);
			break;
		case 1:
			// %1 #0 UnaryOperator() #1 Expression() #2 ExpressionPrime()
			final NodeSequence seq3 = (NodeSequence) ich;
			// #0 UnaryOperator()
			final INode seq4 = seq3.elementAt(0);
			this.pushOperator(new UnaryOperatorToken((UnaryOperator) seq4));
			// #1 Expression()
			final INode seq5 = seq3.elementAt(1);
			seq5.accept(this);
			// #2 ExpressionPrime()
			final INode seq6 = seq3.elementAt(2);
			seq6.accept(this);
			break;
		case 2:
			// %2 #0 <PARENTHESIS_LEFT> #1 Expression() #2 <PARENTHESIS_RIGHT>
			// #3 ExpressionPrime()
			final NodeSequence seq7 = (NodeSequence) ich;
			// #0 <PARENTHESIS_LEFT>
			this.pushLeftParenthesis();
			// #1 Expression()
			final INode seq9 = seq7.elementAt(1);
			seq9.accept(this);
			// #2 <PARENTHESIS_RIGHT>
			this.pushRightParenthesis();
			// #3 ExpressionPrime()
			final INode seq11 = seq7.elementAt(3);
			seq11.accept(this);
			break;
		case 3:
			// %3 #0 <INTEGER_LITERAL> #1 ExpressionPrime()
			final NodeSequence seq12 = (NodeSequence) ich;
			// #0 <INTEGER_LITERAL>
			this.output.add(new LiteralToken(IntType.INSTANCE));
			// #1 ExpressionPrime()
			final INode seq14 = seq12.elementAt(1);
			seq14.accept(this);
			break;
		case 4:
			// %4 #0 <TRUE> #1 ExpressionPrime()
			final NodeSequence seq15 = (NodeSequence) ich;
			// #0 <TRUE>
			this.output.add(new LiteralToken(BooleanType.INSTANCE));
			// #1 ExpressionPrime()
			final INode seq17 = seq15.elementAt(1);
			seq17.accept(this);
			break;
		case 5:
			// %5 #0 <FALSE> #1 ExpressionPrime()
			final NodeSequence seq18 = (NodeSequence) ich;
			// #0 <FALSE>
			this.output.add(new LiteralToken(BooleanType.INSTANCE));
			// #1 ExpressionPrime()
			final INode seq20 = seq18.elementAt(1);
			seq20.accept(this);
			break;
		case 6:
			// %6 #0 <THIS> #1 ExpressionPrime()
			final NodeSequence seq21 = (NodeSequence) ich;
			// #0 <THIS>
			this.output.add(new LiteralToken(this.scope.lookupVariable("this")
					.getType()));
			// #1 ExpressionPrime()
			final INode seq23 = seq21.elementAt(1);
			seq23.accept(this);
			break;
		case 7:
			// %7 #0 Identifier() #1 ExpressionPrime()
			final NodeSequence seq24 = (NodeSequence) ich;
			// #0 Identifier()
			final INode seq25 = seq24.elementAt(0);
			this.output.add(new LiteralToken(this.scope.lookupVariable(
					((Identifier) seq25).f0.tokenImage).getType()));
			// #1 ExpressionPrime()
			final INode seq26 = seq24.elementAt(1);
			seq26.accept(this);
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
	 * .. .. | %1 #0 ArrayAccess() #1 ExpressionPrime()<br>
	 * .. .. | %2 #0 ArrayLengthAccess() #1 ExpressionPrime()<br>
	 * .. .. | %3 #0 MethodCall() #1 ExpressionPrime()<br>
	 * .. .. | %4 Empty()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final ExpressionPrime n) {
		// f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()
		// .. .. | %1 #0 ArrayAccess() #1 ExpressionPrime()
		// .. .. | %2 #0 ArrayLengthAccess() #1 ExpressionPrime()
		// .. .. | %3 #0 MethodCall() #1 ExpressionPrime()
		// .. .. | %4 Empty()
		final NodeChoice nch = n.f0;
		final INode ich = nch.choice;
		switch (nch.which) {
		case 0:
			// %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()
			final NodeSequence seq = (NodeSequence) ich;
			// #0 BinaryOperator()
			final INode seq1 = seq.elementAt(0);
			this.pushOperator(new BinaryOperatorToken((BinaryOperator) seq1));
			// #1 Expression()
			final INode seq2 = seq.elementAt(1);
			seq2.accept(this);
			// #2 ExpressionPrime()
			final INode seq3 = seq.elementAt(2);
			seq3.accept(this);
			break;
		case 1:
			// %1 #0 ArrayAccess() #1 ExpressionPrime()
			final NodeSequence seq4 = (NodeSequence) ich;
			// #0 ArrayAccess()
			final INode seq5 = seq4.elementAt(0);
			this.stack.push(new ArrayAccessToken((ArrayAccess) seq5));
			this.pushLeftParenthesis();
			seq5.accept(this);
			this.pushRightParenthesis();
			// #1 ExpressionPrime()
			final INode seq6 = seq4.elementAt(1);
			seq6.accept(this);
			break;
		case 2:
			// %2 #0 ArrayLengthAccess() #1 ExpressionPrime()
			final NodeSequence seq7 = (NodeSequence) ich;
			// #0 ArrayLengthAccess()
			final INode seq8 = seq7.elementAt(0);
			this.stack
					.push(new ArrayLengthAccessToken((ArrayLengthAccess) seq8));
			seq8.accept(this);
			// #1 ExpressionPrime()
			final INode seq9 = seq7.elementAt(1);
			seq9.accept(this);
			break;
		case 3:
			// %3 #0 MethodCall() #1 ExpressionPrime()
			final NodeSequence seq10 = (NodeSequence) ich;
			// #0 MethodCall()
			final INode seq11 = seq10.elementAt(0);
			this.stack.push(new MethodCallToken((MethodCall) seq11));
			seq11.accept(this);
			// #1 ExpressionPrime()
			final INode seq12 = seq10.elementAt(1);
			seq12.accept(this);
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
	 * f3 -> ( #0 Parameter()<br>
	 * .. .. . #1 ( $0 <COMMA> $1 Parameter() )* )?<br>
	 * f4 -> <PARENTHESIS_RIGHT><br>
	 *
	 * @param n
	 *            - the node to visit
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
		this.pushLeftParenthesis();
		// f3 -> ( #0 Parameter()
		// .. .. . #1 ( $0 <COMMA> $1 Parameter() )* )?
		final NodeOptional n3 = n.f3;
		if (n3.present()) {
			final NodeSequence seq = (NodeSequence) n3.node;
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
					while (this.stack.peek() != ParenthesisToken.LEFT) {
						this.output.add(this.stack.pop());
						if (stack.isEmpty()) {
							throw new MismatchedParenthesisException();
						}
					}
					// $1 Parameter()
					final INode seq5 = seq3.elementAt(1);
					seq5.accept(this);
				}
			}
		}
		// f4 -> <PARENTHESIS_RIGHT>
		this.pushRightParenthesis();
	}

	protected void pushOperator(AbstractOperatorToken operator) {
		IToken top = this.stack.peek();
		while (top.isOperator()
				&& (((operator.getAssociativity() == Associativity.LEFT && operator
						.getPrecedence() <= ((AbstractOperatorToken) top)
						.getPrecedence())) || (operator.getAssociativity() == Associativity.RIGHT && operator
						.getPrecedence() < ((AbstractOperatorToken) top)
						.getPrecedence()))) {
			this.output.add(this.stack.pop());
		}
		this.stack.push(operator);
	}

	protected void pushLeftParenthesis() {
		this.stack.push(ParenthesisToken.LEFT);
	}

	protected void pushRightParenthesis() {
		while (this.stack.peek() != ParenthesisToken.LEFT) {
			this.output.add(this.stack.pop());
			if (stack.isEmpty()) {
				throw new MismatchedParenthesisException();
			}
		}
		this.stack.pop();
		if (this.stack.peek().isFunction()) {
			this.output.add(this.stack.pop());
		}
	}

}
