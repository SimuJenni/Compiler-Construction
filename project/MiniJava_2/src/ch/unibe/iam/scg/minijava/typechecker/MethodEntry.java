package ch.unibe.iam.scg.minijava.typechecker;

import java.util.List;

public class MethodEntry implements SymbolTableEntry {
	
	private String name;
	private List<ClassEntry> parameterTypes;
	private ClassEntry returnType;
	private List<VariableEntry> variables;
	private ClassEntry environment;
	
	public MethodEntry(String name, List<ClassEntry> parameterTypes,
			ClassEntry returnType, List<VariableEntry> variables, ClassEntry environment) {
		this.name = name;
		this.parameterTypes = parameterTypes;
		this.returnType = returnType;
		this.variables = variables;
		this.environment = environment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassEntry> getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(List<ClassEntry> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public ClassEntry getReturnType() {
		return returnType;
	}

	public void setReturnType(ClassEntry returnType) {
		this.returnType = returnType;
	}

	public List<VariableEntry> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableEntry> variables) {
		this.variables = variables;
	}

	public ClassEntry getEnvironment() {
		return environment;
	}

	public void setEnvironment(ClassEntry environment) {
		this.environment = environment;
	}

}
