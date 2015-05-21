package ch.unibe.iam.scg.minijava.typechecker.type;

public class Variable {

	protected String name, value;
	protected IType type;
	protected boolean isConstant;
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

	public void setValue(String value) {
		this.value=value;		
		this.isConstant=true;
	}

	public boolean isConstant() {
		return this.isConstant;
	}

	public String getValue() {
		return this.value;
	}

	public void setUnknown() {
		isConstant=false;
	}

}
