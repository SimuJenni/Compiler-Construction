package ch.unibe.iam.scg.minijava.typechecker;

public class VariableEntry implements SymbolTableEntry {

	private String name;
	private ClassEntry type;

	public VariableEntry(String name, ClassEntry type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassEntry getType() {
		return type;
	}

	public void setType(ClassEntry type) {
		this.type = type;
	}

	@Override
	public String getEntryTypeName() {
		// TODO Auto-generated method stub
		return type.getName();
	}

}
