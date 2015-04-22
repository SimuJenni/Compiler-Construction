package ch.unibe.iam.scg.minijava.typechecker;

public class ClassEntry implements SymbolTableEntry {
	private String className;
	private SymbolTable environment;

	public ClassEntry(String className, SymbolTable environment) {
		this.className = className;
		this.environment = environment;
	}

	@Override
	public EntryType getType() {
		return EntryType.CLASS;
	}

}
