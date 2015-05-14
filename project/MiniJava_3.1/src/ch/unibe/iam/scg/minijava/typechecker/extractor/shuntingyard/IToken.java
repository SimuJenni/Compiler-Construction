package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public interface IToken {

	public IType evaluate(IScope scope, Stack<IToken> stack);

	public boolean isLiteral();

	public boolean isOperator();

	public boolean isParenthesis();

	public boolean isFunction();

}
