package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;

public class NodeToken implements IToken {

	protected INode node;

	public NodeToken(INode node) {
		this.node = node;
	}

	public INode getNode() {
		return this.node;
	}

	@Override
	public boolean isNode() {
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
