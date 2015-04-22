package ch.unibe.iam.scg.minijava.typechecker;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {
	private SymbolTableBuilder builder;

	public TypeChecker() {
		builder = new SymbolTableBuilder();
	}

	public boolean check(Object node) {
		SymbolTable symbolTable = builder.build(node);
		return false;
	}

}
