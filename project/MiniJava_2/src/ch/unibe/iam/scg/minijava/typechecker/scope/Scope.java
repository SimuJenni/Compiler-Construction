package ch.unibe.iam.scg.minijava.typechecker.scope;

import java.util.HashMap;
import java.util.Map;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class Scope implements IScope {

	protected IScope parent;
	protected Map<String, IType> types;
	protected Map<String, IScope> typeScopes;
	protected Map<String, Method> methods;
	protected Map<String, Variable> variables;

	public Scope(IScope parent) {
		this.parent = parent;
		this.types = new HashMap<String, IType>();
		this.typeScopes = new HashMap<String, IScope>();
		this.methods = new HashMap<String, Method>();
		this.variables = new HashMap<String, Variable>();
	}

	@Override
	public IScope getParent() {
		return this.parent;
	}

	@Override
	public void setParent(IScope parent) {
		this.parent = parent;
	}

	@Override
	public boolean hasType(String name) {
		return this.types.containsKey(name);
	}

	@Override
	public IType getType(String name) {
		if (!this.hasType(name)) {
			throw new LookupException(name);
		}
		return this.types.get(name);
	}

	@Override
	public void putType(String name, IType type, IScope scope) {
		if (this.hasType(name)) {
			throw new NameCollisionException(name);
		}
		this.types.put(name, type);
		this.typeScopes.put(name, scope);
	}

	@Override
	public IScope lookupTypeScope(String name) throws LookupException {
		if (!this.hasType(name)) {
			throw new LookupException(name);
		}
		return this.typeScopes.get(name);
	}

	@Override
	public IType lookupType(String name) throws LookupException {
		if (this.hasType(name)) {
			return this.getType(name);
		}
		return this.parent.lookupType(name);
	}

	@Override
	public boolean hasMethod(String name) {
		return this.methods.containsKey(name);
	}

	@Override
	public Method getMethod(String name) {
		if (!this.hasMethod(name)) {
			throw new LookupException(name);
		}
		return this.methods.get(name);
	}

	@Override
	public void putMethod(String name, Method method) {
		if (this.hasMethod(name)) {
			throw new NameCollisionException(name);
		}
		this.methods.put(name, method);
	}

	@Override
	public Method lookupMethod(String name) throws LookupException {
		if (this.hasMethod(name)) {
			return this.getMethod(name);
		}
		return this.parent.lookupMethod(name);
	}

	@Override
	public boolean hasVariable(String name) {
		return this.variables.containsKey(name);
	}

	@Override
	public Variable getVariable(String name) {
		if (!this.hasVariable(name)) {
			throw new LookupException(name);
		}
		return this.variables.get(name);
	}

	@Override
	public void putVariable(String name, Variable variable) {
		if (this.hasVariable(name)) {
			throw new NameCollisionException(name);
		}
		this.variables.put(name, variable);
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		if (this.hasVariable(name)) {
			return this.getVariable(name);
		}
		return this.parent.lookupVariable(name);
	}

}
