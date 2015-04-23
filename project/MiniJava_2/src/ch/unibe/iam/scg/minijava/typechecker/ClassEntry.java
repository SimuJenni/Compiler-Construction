package ch.unibe.iam.scg.minijava.typechecker;

public class ClassEntry extends SymbolTable implements SymbolTableEntry {

	private String name;
	private ClassEntry superClass;
	private SymbolTable parent;
	private boolean isInitialized=false;

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

	public SymbolTableEntry lookup(String key) {
		try {
			return this.get(key);
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

	public boolean canBeAssignedTo(ClassEntry classEntry) {
		if (classEntry == this) {
			return true;
		}
		return this.superClass.canBeAssignedTo(classEntry);
	}

	public boolean isInitialized() {
		if(isInitialized)
			return true;
		else{
			isInitialized=true;
			return false;
		}
	}

}
