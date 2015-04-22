package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;

public class ClassEntryBuilder extends SymbolTableBuilder {

	public ClassEntryBuilder(ClassEntry classEntry) {
		super(classEntry);
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
		super.visit(n);
	}

}
