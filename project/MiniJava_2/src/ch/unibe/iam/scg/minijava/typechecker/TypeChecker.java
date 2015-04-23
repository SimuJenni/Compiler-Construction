package ch.unibe.iam.scg.minijava.typechecker;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {
	private GlobalSymbolTableBuilder builder;
	private ExpressionVisitor expressionVisitor;

	public TypeChecker() {
		builder = new GlobalSymbolTableBuilder(new SymbolTable());
		expressionVisitor = new ExpressionVisitor(builder.getTable());
	}

	public boolean check(Object node) {
		try {
			builder.build(node);
			return expressionVisitor.check(node);
		} catch (SymbolNotFoundException exception) {
			return false;
		}
	}

}
