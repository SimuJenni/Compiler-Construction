package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class SymbolTableBuilder extends DepthFirstVoidVisitor {

	protected SymbolTable table;

	public SymbolTableBuilder(SymbolTable table) {
		this.table = table;
	}

	public SymbolTable build(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return table;
	}

	/**
	 * Visits a {@link VarDeclaration} node, whose children are the following :
	 * <p>
	 * f0 -> Type()<br>
	 * f1 -> Identifier()<br>
	 * f2 -> <SEMICOLON><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final VarDeclaration n) {
		String typeName = n.f0.accept(new TypeNameExtractor());
		String name = n.f1.accept(new IdentifierNameExtractor());
		// TODO get rid of nasty cast
		this.table.put(name, new VariableEntry(name, (ClassEntry) this.table.get(typeName)));
		super.visit(n);
	}

}
