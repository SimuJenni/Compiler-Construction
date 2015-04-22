package ch.unibe.iam.scg.minijava.typechecker;

public class ClassEntry extends SymbolTable implements SymbolTableEntry {

	private String name;
	private ClassEntry superClass;
	private SymbolTable parent;

	public ClassEntry(String name, ClassEntry superClass, SymbolTable parent) {
		this.name = name;
		this.superClass = superClass;
		this.parent = parent;
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

	public SymbolTable getParent() {
		return parent;
	}

	public void setParent(SymbolTable parent) {
		this.parent = parent;
	}

	@Override
	public boolean containsKey(String key) {
		return super.containsKey(key) || this.superClass.containsKey(key)
				|| this.parent.containsKey(key);
	}

	@Override
	public SymbolTableEntry get(String key) {
		try {
			return super.get(key);
		} catch (SymbolNotFoundException exception) {
			// noop
		}
		try {
			return this.superClass.get(key);
		} catch (SymbolNotFoundException exception) {
			// noop
		}
		return this.parent.get(key);
	}

}
