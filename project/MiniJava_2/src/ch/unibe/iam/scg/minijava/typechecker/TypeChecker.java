package ch.unibe.iam.scg.minijava.typechecker;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {
	private GlobalSymbolTableBuilder builder;

	public TypeChecker() {
		builder = new GlobalSymbolTableBuilder(new SymbolTable());
	}

	public boolean check(Object node) {
		SymbolTable symbolTable = builder.build(node);
		return false;
	}

}
