package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class ExpressionVisitor extends DepthFirstVoidVisitor {
	private SymbolTable table;

	public ExpressionVisitor(SymbolTable table) {
		this.table=table;
	}
	
	

}
