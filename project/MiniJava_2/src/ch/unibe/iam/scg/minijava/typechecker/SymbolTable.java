package ch.unibe.iam.scg.minijava.typechecker;

import java.util.HashMap;

/**
 * Root symbol-table in the symbol-table hierarchy. Stores references to the
 * symbol-tables of all the classes.
 */
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

}
