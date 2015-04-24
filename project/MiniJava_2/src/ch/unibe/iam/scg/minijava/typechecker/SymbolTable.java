package ch.unibe.iam.scg.minijava.typechecker;

import java.util.HashMap;

public class SymbolTable implements SymbolTableEntry {

	private HashMap<String, SymbolTableEntry> entries;

	public SymbolTable() {
		entries = new HashMap<String, SymbolTableEntry>();
	}

	public void put(String key, SymbolTableEntry entry) {
		entries.put(key, entry);
	}

	public SymbolTableEntry get(String key) {
		if (!this.containsKey(key)) {
			throw new SymbolNotFoundException(key);
		}
		return entries.get(key);
	}

	public boolean containsKey(String key) {
		return entries.containsKey(key);
	}

	public SymbolTableEntry lookup(String key) {
		return this.get(key);
	}

	@Override
	public String getEntryTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

}
