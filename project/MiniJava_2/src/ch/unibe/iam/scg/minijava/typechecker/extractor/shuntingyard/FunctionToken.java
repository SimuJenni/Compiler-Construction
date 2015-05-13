package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;

public class FunctionToken extends NodeToken {

	public FunctionToken(INode node) {
		super(node);
	}

	@Override
	public boolean isNode() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
