package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class ClassScope extends AbstractScope {

	protected IType type;

	public ClassScope(IScope parent, IType type) {
		super(parent);
		this.type = type;
	}

	@Override
	public Method lookupMethod(String name) throws LookupException {
		if (this.type.hasMethod(name)) {
			return this.type.getMethod(name);
		}
		return super.lookupMethod(name);
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		if (this.type.hasVariable(name)) {
			return this.type.getVariable(name);
		}
		return super.lookupVariable(name);
	}

}
