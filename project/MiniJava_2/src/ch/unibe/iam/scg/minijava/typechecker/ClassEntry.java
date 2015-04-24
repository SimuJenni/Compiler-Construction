package ch.unibe.iam.scg.minijava.typechecker;

public class ClassEntry extends SymbolTable implements SymbolTableEntry {

	private String name;
	private ClassEntry superClass;
	SymbolTable parent;
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
			return this.superClass.lookup(key);
		} catch (SymbolNotFoundException exception) {
			// noop
		}
		return this.parent.lookup(key);
	}

	public boolean canBeAssignedTo(ClassEntry classEntry) {
		if (classEntry == this) {
			return true;
		}
		try{
		return this.superClass.canBeAssignedTo(classEntry);
		} catch (NullPointerException e){
			throw new TypeCheckException();
		}
	}

	public boolean isInitialized() {
		if(isInitialized)
			return true;
		else{
			isInitialized=true;
			return false;
		}
	}
	
	@Override
	public String getEntryTypeName() {
		// TODO Auto-generated method stub
		return name;
	}

}
