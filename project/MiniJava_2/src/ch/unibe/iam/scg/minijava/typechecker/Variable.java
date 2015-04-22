package ch.unibe.iam.scg.minijava.typechecker;

public class Variable implements SymbolTableEntry {
	
	private String id, type;

	public Variable(String id, String type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public EntryType getType() {
		return EntryType.VARIABLE;
	}

}
