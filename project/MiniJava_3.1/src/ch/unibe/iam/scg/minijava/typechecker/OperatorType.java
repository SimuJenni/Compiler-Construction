package ch.unibe.iam.scg.minijava.typechecker;

public enum OperatorType {
	ADD("+"), MINUS("-"), MULT("*"), GREATER_THAN(">"), AND("&&"), NOT("!"), 
	BRACKET_LEFT("["), BRACKET_RIGHT("]"), LENGTH(".length"), DOT(".");
	
	private String name;
	private String precedence;

	OperatorType(String name) {
		this.name = name;
		this.precedence="&&>+-*.length!]";
	}

	public String getName() {
		return this.name;
	}

	public boolean hasHigherPrecedence(OperatorType operatorType) {
		// TODO Auto-generated method stub
		return precedence.indexOf(name)>precedence.indexOf(operatorType.getName());
	}

}
