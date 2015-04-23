package ch.unibe.iam.scg.minijava.typechecker.type;

public class Variable {

	protected String name;
	protected Type type;

	public Variable(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

}
