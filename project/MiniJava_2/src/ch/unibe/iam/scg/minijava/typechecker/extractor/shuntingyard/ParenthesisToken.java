package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

public enum ParenthesisToken implements IToken {
	
	LEFT(), RIGHT();

	@Override
	public boolean isNode() {
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

}
