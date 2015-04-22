package ch.unibe.iam.scg.minijava.typechecker;

import java.util.List;

public class MethodEntry extends SymbolTable implements SymbolTableEntry {

	private String name;
	private List<ClassEntry> parameterTypes;
	private ClassEntry returnType;
	private SymbolTable parent;

	public MethodEntry(String name, List<ClassEntry> parameterTypes,
			ClassEntry returnType, SymbolTable parent) {
		this.name = name;
		this.parameterTypes = parameterTypes;
		this.returnType = returnType;
		this.setParent(parent);
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

	public SymbolTable getParent() {
		return parent;
	}

	public void setParent(SymbolTable parent) {
		this.parent = parent;
	}

}
