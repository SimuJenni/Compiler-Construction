package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.HashMap;
import java.util.Map;

public class Method {

	protected IType type;
	protected String name;
	protected IType returnType;
	protected Map<String, Variable> parameters;
	protected Map<String, Variable> variables;

	public Method(IType type, String name, IType returnType) {
		this.type = type;
		this.name = name;
		this.returnType = returnType;
		this.parameters = new HashMap<String, Variable>();
		this.variables = new HashMap<String, Variable>();
	}

	public IType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public boolean hasParameter(String name) {
		return this.parameters.containsKey(name);
	}

	public Variable getParameter(String name) {
		assert this.hasParameter(name);
		return this.parameters.get(name);
	}

	public void putParameter(String name, Variable parameter) {
		assert !this.hasParameter(name);
		this.parameters.put(name, parameter);
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

}
