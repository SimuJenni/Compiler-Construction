package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;

public class OperatorToken implements IToken {

	protected static final String PRECEDENCE = "&&>+-*!";

	public static OperatorToken build(UnaryOperator node) {
		return new OperatorToken(node.f0.tokenImage, Arity.UNARY,
				Associativity.RIGHT);
	}

	public static OperatorToken build(BinaryOperator node) {
		return new OperatorToken(node.f0.tokenImage, Arity.BINARY,
				Associativity.LEFT);
	}

	protected String symbol;
	protected Arity arity;
	protected Associativity associativity;

	public OperatorToken(String symbol, Arity arity, Associativity associativity) {
		this.symbol = symbol;
		this.arity = arity;
		this.associativity = associativity;
	}

	public int getPrecedence() {
		return PRECEDENCE.indexOf(symbol);
	}

	public String getSymbol() {
		return this.symbol;
	}

	public Arity getArity() {
		return this.arity;
	}

	public Associativity getAssociativity() {
		return this.associativity;
	}

	@Override
	public boolean isNode() {
		return false;
	}

	@Override
	public boolean isOperator() {
		return true;
	}

	@Override
	public boolean isParenthesis() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
