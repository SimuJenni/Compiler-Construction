package ch.unibe.iam.scg.minijava.typechecker;

public class ClassEntry implements SymbolTableEntry {

	private String name;
	private ClassEntry superClass;
	private SymbolTable environment;

	public ClassEntry(String name, ClassEntry superClass,
			SymbolTable environment) {
		this.name = name;
		this.superClass = superClass;
		this.environment = environment;
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

	public SymbolTable getEnvironment() {
		return environment;
	}

	public void setEnvironment(SymbolTable environment) {
		this.environment = environment;
	}

}
