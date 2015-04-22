package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class SymbolTableBuilder extends DepthFirstVoidVisitor {
	private SymbolTable table;

	public SymbolTableBuilder() {
		this.table = new SymbolTable();
	}

}
