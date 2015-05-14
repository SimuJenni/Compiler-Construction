package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;
import ch.unibe.iam.scg.minijava.bcel.generator.CodeGeneratorVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class LiteralToken implements IToken {

	public IType type;
	public String value;

	public LiteralToken(IType type, String value) {
		this.type = type;
		this.value = value;
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


}
