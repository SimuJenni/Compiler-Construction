package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractType implements IType {

	protected String name;
	protected IType superType;
	protected Map<String, Variable> variables;
	protected Map<String, Method> methods;

	public AbstractType(String name, IType superType) {
		this.name = name;
		this.superType = superType;
		this.variables = new HashMap<String, Variable>();
		this.methods = new HashMap<String, Method>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IType getSuperType() {
		return this.superType;
	}

	@Override
	public boolean hasVariable(String name) {
		return this.variables.containsKey(name);
	}

	@Override
	public Variable getVariable(String name) {
		assert this.hasVariable(name);
		return this.variables.get(name);
	}

	@Override
	public void putVariable(String name, Variable variable) {
		assert !this.hasVariable(name);
		this.variables.put(name, variable);
	}

	@Override
	public boolean hasMethod(String name) {
		return this.methods.containsKey(name);
	}

	@Override
	public Method getMethod(String name) {
		assert this.hasMethod(name);
		return this.methods.get(name);
	}

	@Override
	public void putMethod(String name, Method method) {
		assert !this.hasVariable(name);
		this.methods.put(name, method);
	}

}
