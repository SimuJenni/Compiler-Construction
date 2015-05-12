package ch.unibe.iam.scg.minijava.typechecker.type;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.Scope;

public class Type implements IType {

	protected String name;
	protected IType parent;
	protected IScope scope;

	public Type(String name, IType parent) {
		this.name = name;
		this.parent = parent;
		this.scope = new Scope(parent.getScope());
		Variable thisVariable = new Variable("this", this);
		this.scope.putVariable(thisVariable.getName(), thisVariable);
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

	@Override
	public IScope getScope(){
		return this.scope;
	}

}
