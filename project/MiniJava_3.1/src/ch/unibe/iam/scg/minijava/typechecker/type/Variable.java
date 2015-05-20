package ch.unibe.iam.scg.minijava.typechecker.type;

public class Variable {

	protected String name;
	protected IType type;
	protected int assignementCount = 0;

	public Variable(String name, IType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public IType getType() {
		return type;
	}

	public int getAssignmentCount() {
		// TODO Auto-generated method stub
		return assignementCount;
	}

	public void increaseCount() {
		assignementCount++;		
	}


}
