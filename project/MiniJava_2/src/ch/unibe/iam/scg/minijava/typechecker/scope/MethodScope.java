package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class MethodScope extends AbstractScope {

	protected Method method;

	public MethodScope(IScope parent, Method method) {
		super(parent);
		this.method = method;
		Variable thisVariable = new Variable("this", method.getType());
		this.method.putVariable(thisVariable.getName(), thisVariable);
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		if (this.method.hasParameter(name)) {
			return this.method.getParameter(name);
		}
		if (this.method.hasVariable(name)) {
			return this.method.getVariable(name);
		}
		return super.lookupVariable(name);
	}

}
