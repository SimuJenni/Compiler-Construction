package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {

	private SymbolTable globalSymbolTable;

	public TypeChecker() {
		this.globalSymbolTable = new SymbolTable();
		ClassEntry nullClassEntry = new NullClassEntry();
		this.globalSymbolTable.put(Types.INT_ARRAY.getName(), new ClassEntry(
				Types.INT_ARRAY.getName(), nullClassEntry,
				this.globalSymbolTable));
		this.globalSymbolTable.put(Types.INT.getName(), new ClassEntry(
				Types.INT.getName(), nullClassEntry, this.globalSymbolTable));
		this.globalSymbolTable.put(Types.STRING_ARRAY.getName(),
				new ClassEntry(Types.STRING_ARRAY.getName(), nullClassEntry,
						this.globalSymbolTable));
		this.globalSymbolTable
				.put(Types.STRING.getName(),
						new ClassEntry(Types.STRING.getName(), nullClassEntry,
								this.globalSymbolTable));
		this.globalSymbolTable.put(Types.BOOLEAN.getName(),
				new ClassEntry(Types.BOOLEAN.getName(), nullClassEntry,
						this.globalSymbolTable));
		this.globalSymbolTable.put(Types.VOID.getName(), new ClassEntry(
				Types.VOID.getName(), nullClassEntry, this.globalSymbolTable));
	}

	public boolean check(Object n) {
		INode node = (INode) n;
		return node.accept(new SymbolTableBuilderDelegator(
				this.globalSymbolTable));
	}

}
