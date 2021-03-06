package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;
import ch.unibe.iam.scg.minijava.bcel.generator.CodeGeneratorVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public enum ParenthesisToken implements IToken {

	LEFT(), RIGHT();

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLiteral() {
		return false;
	}

	@Override
	public boolean isOperator() {
		return false;
	}

	@Override
	public boolean isParenthesis() {
		return true;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	@Override
	public void accept(CodeGeneratorVisitor vis) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isVariable() {
		return false;
	}


}
