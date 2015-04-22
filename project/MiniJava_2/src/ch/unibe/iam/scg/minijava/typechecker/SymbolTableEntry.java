package ch.unibe.iam.scg.minijava.typechecker;

public interface SymbolTableEntry {

	enum EntryType{
        CLASS,
        METHOD,
        VARIABLE
    }
	
	public EntryType getType();
}
