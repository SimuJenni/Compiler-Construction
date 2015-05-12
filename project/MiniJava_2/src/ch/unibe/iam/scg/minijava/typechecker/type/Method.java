package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.List;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.Scope;

public class Method {

	protected String name;
	protected IType returnType;
	protected List<Variable> parameters;
	protected IScope scope;

	public Method(IScope parent, String name, IType returnType, List<Variable> parameters) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
		this.scope = new Scope(parent);
		for (Variable parameter : parameters) {
			this.scope.putVariable(parameter.getName(), parameter);
		}
	}

	public String getName() {
		return this.name;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public List<Variable> getParameters() {
		return this.parameters;
	}

	public IScope getScope() {
		return this.scope;
	}

}
