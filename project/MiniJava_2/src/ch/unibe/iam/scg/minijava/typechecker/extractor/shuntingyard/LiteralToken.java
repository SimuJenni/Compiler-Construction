package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class LiteralToken implements IToken {

	protected IType type;

	public LiteralToken(IType type) {
		this.type = type;
	}

	@Override
	public IType evaluate(IScope scope, IType... parameterTypes) {
		if (parameterTypes.length != 0) {
			throw new IllegalArgumentException("parameters must be empty");
		}
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
