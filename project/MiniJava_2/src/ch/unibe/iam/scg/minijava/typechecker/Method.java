package ch.unibe.iam.scg.minijava.typechecker;

public class Method implements SymbolTableEntry {
	
	private String methodName, returnType;
	private SymbolTable environment;

	public Method(String methodName, String returnType, SymbolTable environment) {
		this.methodName = methodName;
		this.returnType = returnType;
		this.environment = environment;
	}

	@Override
	public EntryType getType() {
		return EntryType.METHOD;
	}

}
