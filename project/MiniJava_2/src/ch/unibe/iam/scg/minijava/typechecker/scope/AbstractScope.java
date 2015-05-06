package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public abstract class AbstractScope implements IScope {

	protected IScope parent;

	public AbstractScope(IScope parent) {
		this.parent = parent;
	}

	@Override
	public IType lookupType(String name) throws LookupException {
		return this.parent.lookupType(name);
	}

	@Override
	public Method lookupMethod(String name) throws LookupException {
		return this.parent.lookupMethod(name);
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		return this.parent.lookupVariable(name);
	}

}
