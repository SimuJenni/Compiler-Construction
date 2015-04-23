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
		try {
			builder.build(node);
			return true;
		} catch (TypeCheckException exception) {
			return false;
		}
	}

}
