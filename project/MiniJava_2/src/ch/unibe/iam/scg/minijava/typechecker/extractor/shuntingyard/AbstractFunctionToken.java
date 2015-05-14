package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;

public abstract class AbstractFunctionToken<T extends INode> implements IToken {

	protected T node;

	public AbstractFunctionToken(T node) {
		this.node = node;
	}

	public T getNode() {
		return this.node;
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
		return false;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
