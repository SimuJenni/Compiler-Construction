package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.Map;

public class Method {

	protected String name;
	protected Type returnType;
	protected Map<String, Variable> parameters;
	protected Map<String, Variable> variables;

	public Method(String name, Type returnType,
			Map<String, Variable> parameters, Map<String, Variable> variables) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
		this.variables = variables;
	}

	public String getName() {
		return this.name;
	}

	public Type getReturnType() {
		return this.returnType;
	}

	public Map<String, Variable> getParameters() {
		return this.parameters;
	}

	public Map<String, Variable> getVariables() {
		return this.variables;
	}

}
