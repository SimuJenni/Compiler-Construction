package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.minijava.bcel.generator.CodeGeneratorVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class VariableToken implements IToken {

	protected IType type;
	protected String name;

	public VariableToken(IType type, String name) {
		this.type = type;
		this.name = name;
	}

	public IType getType() {
		return type;
	}

	public String getName() {
		return name;
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

	@Override
	public void accept(CodeGeneratorVisitor vis) {
		vis.visit(this);
	}
	
	@Override
	public boolean isVariable() {
		return true;
	}

}
