package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.HashMap;
import java.util.Map;

public class Type {

	protected String name;
	protected Type superType;
	protected Map<String, Variable> variables;
	protected Map<String, Method> methods;

	public Type(String name, Type superType) {
		this.name = name;
		this.superType = superType;
		this.variables = new HashMap<String, Variable>();
		this.methods = new HashMap<String, Method>();
	}

	public String getName() {
		return this.name;
	}

	public Type getSuperType() {
		return this.superType;
	}

	public boolean hasVariable(String name) {
		return this.variables.containsKey(name);
	}

	public Variable getVariable(String name) {
		assert this.hasVariable(name);
		return this.variables.get(name);
	}

	public void putVariable(String name, Variable variable) {
		assert !this.hasVariable(name);
		this.variables.put(name, variable);
	}

	public boolean hasMethod(String name) {
		return this.methods.containsKey(name);
	}

	public Method getMethod(String name) {
		assert this.hasMethod(name);
		return this.methods.get(name);
	}

	public void putMethod(String name, Method method) {
		assert !this.hasVariable(name);
		this.methods.put(name, method);
	}

}
