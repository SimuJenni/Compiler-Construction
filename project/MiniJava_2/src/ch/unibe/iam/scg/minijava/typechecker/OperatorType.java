package ch.unibe.iam.scg.minijava.typechecker;

public enum OperatorType {
	ADD("+"), MINUS("-"), MULT("*"), GREATER_THAN(">"), AND("&&"), NOT("!");
	
	private String name;
	private String precedence;

	OperatorType(String name) {
		this.name = name;
		this.precedence="&&>+-*!";
	}

	public String getName() {
		return this.name;
	}

	public boolean hasHigherPrecedence(OperatorType operatorType) {
		// TODO Auto-generated method stub
		return precedence.indexOf(name)>precedence.indexOf(operatorType.getName());
	}

}
