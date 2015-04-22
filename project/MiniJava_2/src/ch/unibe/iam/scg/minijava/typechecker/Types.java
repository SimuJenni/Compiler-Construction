package ch.unibe.iam.scg.minijava.typechecker;

public enum Types {
	
	INT_ARRAY("int[]"), INT("int"), BOOLEAN("boolean"), VOID("void");

	private String name;

	Types(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
