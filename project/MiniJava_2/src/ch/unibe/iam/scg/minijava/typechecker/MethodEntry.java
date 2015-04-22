package ch.unibe.iam.scg.minijava.typechecker;

import java.util.List;

public class MethodEntry implements SymbolTableEntry {

	private String name;
	private List<ClassEntry> parameterTypes;
	private ClassEntry returnType;
	private ClassEntry definingType;
	private ClassEntry environment;

	public MethodEntry(String name, List<ClassEntry> parameterTypes,
			ClassEntry returnType, ClassEntry definingType,
			ClassEntry environment) {
		this.name = name;
		this.parameterTypes = parameterTypes;
		this.returnType = returnType;
		this.setDefiningType(definingType);
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

	public ClassEntry getDefiningType() {
		return definingType;
	}

	public void setDefiningType(ClassEntry definingType) {
		this.definingType = definingType;
	}

	public ClassEntry getEnvironment() {
		return environment;
	}

	public void setEnvironment(ClassEntry environment) {
		this.environment = environment;
	}

}
