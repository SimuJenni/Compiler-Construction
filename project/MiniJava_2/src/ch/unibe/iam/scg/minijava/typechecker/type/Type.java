package ch.unibe.iam.scg.minijava.typechecker.type;


public class Type implements IType {

	protected String name;
	protected IType parent;

	public Type(String name, IType parent) {
		this.name = name;
		this.parent = parent;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IType getParent() {
		return this.parent;
	}

	@Override
	public boolean canBeAssignedTo(IType type) {
		if (type == this) {
			return true;
		}
		return this.parent.canBeAssignedTo(type);
	}

}
