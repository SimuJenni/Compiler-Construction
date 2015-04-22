package ch.unibe.iam.scg.minijava.typechecker;

import java.util.List;

public class ClassEntry implements SymbolTableEntry {

	private String name;
	private ClassEntry superClass;
	private List<VariableEntry> variables;
	private List<MethodEntry> methods;

	public ClassEntry(String name, ClassEntry superClass,
			List<VariableEntry> variables, List<MethodEntry> methods) {
		this.name = name;
		this.superClass = superClass;
		this.variables = variables;
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassEntry getSuperClass() {
		return superClass;
	}

	public void setSuperClass(ClassEntry superClass) {
		this.superClass = superClass;
	}

	public List<VariableEntry> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableEntry> variables) {
		this.variables = variables;
	}

	public List<MethodEntry> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodEntry> methods) {
		this.methods = methods;
	}

}
