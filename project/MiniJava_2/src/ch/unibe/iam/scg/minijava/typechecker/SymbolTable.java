package ch.unibe.iam.scg.minijava.typechecker;

import java.util.HashMap;
import java.util.Stack;

/*
 * Root symbol-table in the symbol-table hierarchy.
 * Stores references to the symbol-tables of all the classes.
 */
public class SymbolTable {
	private HashMap<String, SymbolTableEntry> symbolTable;
	
	public SymbolTable() {
		symbolTable=new HashMap<String,SymbolTableEntry>();
	}
	
	public void putVariable(String key, Variable id){
		symbolTable.put(key, id);
	}
	
	public void putMethod(String key, Method m){
		symbolTable.put(key, m);
	}
	
	public void putClass(String key, ClassEntry c){
		symbolTable.put(key, c);
	}
	
	public SymbolTableEntry get(String key){
		return symbolTable.get(key);
	}
	
}
