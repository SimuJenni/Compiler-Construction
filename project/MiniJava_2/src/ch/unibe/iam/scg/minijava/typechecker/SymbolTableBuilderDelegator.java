package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.Goal;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class SymbolTableBuilderDelegator extends DepthFirstRetVisitor<Boolean> {

	private SymbolTable table;

	public SymbolTableBuilderDelegator(SymbolTable table) {
		super();
		this.table = table;
	}

	@Override
	public Boolean visit(Goal node) {
		try {
			(new GlobalSymbolTableBuilder(this.table)).build(node);
			return true;
		} catch (TypeCheckException exception) {
			return false;
		}
	}

	@Override
	public Boolean visit(ClassDeclaration node) {
		try {
			(new GlobalSymbolTableBuilder(this.table)).build(node);
			return true;
		} catch (TypeCheckException exception) {
			return false;
		}
	}

	@Override
	public Boolean visit(MethodDeclaration node) {
		try {
			(new ClassEntryBuilder(new ClassEntry("anonymous",
					new NullClassEntry(), this.table))).build(node);
			return true;
		} catch (TypeCheckException exception) {
			return false;
		}
	}

	@Override
	public Boolean visit(Expression node) {
		return (new ExpressionVisitor(this.table)).check(node);
	}

	@Override
	public Boolean visit(Statement node) {
		return (new StatementVisitor(this.table)).check(node);
	}
}
