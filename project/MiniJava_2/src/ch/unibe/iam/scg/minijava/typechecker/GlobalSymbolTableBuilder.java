package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;

public class GlobalSymbolTableBuilder extends SymbolTableBuilder {

	public GlobalSymbolTableBuilder(SymbolTable table) {
		super(table);
	}

	/**
	 * Visits a {@link ClassDeclaration} node, whose children are the following
	 * :
	 * <p>
	 * f0 -> <CLASS><br>
	 * f1 -> Identifier()<br>
	 * f2 -> ( #0 <EXTENDS> #1 Identifier() )?<br>
	 * f3 -> <BRACE_LEFT><br>
	 * f4 -> ( VarDeclaration() )*<br>
	 * f5 -> ( MethodDeclaration() )*<br>
	 * f6 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(ClassDeclaration n) {
		String name = n.f1.f0.tokenImage;
		String superClassName = n.f2.accept(new IdentifierNameExtractor());
		// TODO use null object here
		ClassEntry superClass = null;
		if (superClassName != null) {
			// TODO get rid of nasty cast
			superClass = (ClassEntry) this.table.get(superClassName);
		}
		ClassEntry classEntry = new ClassEntry(name, superClass, this.table);
		this.table.put(name, classEntry);
		ClassEntryBuilder classEntryBuilder = new ClassEntryBuilder(classEntry);
		// delegate
		n.accept(classEntryBuilder);
	}

}
