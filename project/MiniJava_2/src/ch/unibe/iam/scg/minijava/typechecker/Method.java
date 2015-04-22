package ch.unibe.iam.scg.minijava.typechecker;

public class Method implements SymbolTableEntry {
	
	private String methodName, returnType;
	private SymbolTable environment;

	@Override
	public EntryType getType() {
		return EntryType.METHOD;
	}

}
