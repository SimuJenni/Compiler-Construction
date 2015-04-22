package ch.unibe.iam.scg.minijava.typechecker;

public class NullClassEntry extends ClassEntry {

	public NullClassEntry() {
		super(null, null, null);
	}
	
	@Override
	public boolean containsKey(String key) {
		return false;
	}
	
	@Override
	public SymbolTableEntry get(String key) {
		throw new SymbolNotFoundException(key);
	}
	
	@Override
	public void put(String key, SymbolTableEntry entry) {
		// noop
	}

}
