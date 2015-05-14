package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class LiteralToken implements IToken {

	protected IType type;

	public LiteralToken(IType type) {
		this.type = type;
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		return this.type;
	}

	@Override
	public boolean isLiteral() {
		return true;
	}

	@Override
	public boolean isOperator() {
		return false;
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
