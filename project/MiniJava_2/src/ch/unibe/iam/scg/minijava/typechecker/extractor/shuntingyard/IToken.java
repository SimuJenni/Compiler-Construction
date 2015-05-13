package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

public interface IToken {

	public boolean isNode();

	public boolean isOperator();

	public boolean isParenthesis();

	public boolean isFunction();

}
