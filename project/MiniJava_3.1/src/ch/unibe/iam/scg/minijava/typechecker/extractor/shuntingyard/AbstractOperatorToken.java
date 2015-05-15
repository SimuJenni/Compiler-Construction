package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;

public abstract class AbstractOperatorToken implements IToken {

	protected static final String PRECEDENCE = "&&><==+-*!";

	protected static IType getParameterType(String symbol) {
		if ("&&!".contains(symbol)) {
			return BooleanType.INSTANCE;
		} else if ("<>==+-*".contains(symbol)) {
			return IntType.INSTANCE;
		}
		throw new IllegalArgumentException("'" + symbol + "' is unknown");
	}

	protected static IType getReturnType(String symbol) {
		if ("&&<>==!".contains(symbol)) {
			return BooleanType.INSTANCE;
		} else if ("+-*".contains(symbol)) {
			return IntType.INSTANCE;
		}
		throw new IllegalArgumentException("'" + symbol + "' is unknown");
	}

	protected String symbol;
	protected Associativity associativity;
	protected IType parameterType;
	protected IType returnType;

	public AbstractOperatorToken(String symbol, Associativity associativity,
			IType parameterType, IType returnType) {
		this.symbol = symbol;
		this.associativity = associativity;
		this.parameterType = parameterType;
		this.returnType = returnType;
	}

	public AbstractOperatorToken(String symbol, Associativity associativity) {
		this(symbol, associativity, getParameterType(symbol),
				getReturnType(symbol));
	}

	public int getPrecedence() {
		return PRECEDENCE.indexOf(symbol);
	}

	public String getSymbol() {
		return this.symbol;
	}

	public Associativity getAssociativity() {
		return this.associativity;
	}

	@Override
	public boolean isLiteral() {
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
