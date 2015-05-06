package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignee;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.If;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileLoop;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class SymbolTableBuilder<T extends SymbolTable> extends
		DepthFirstVoidVisitor {

	protected T table;

	public SymbolTableBuilder(T table) {
		super();
		this.table = table;
	}

	public T build(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return table;
	}

	/**
	 * Visits a {@link VarDeclaration} node, whose children are the following :
	 * <p>
	 * f0 -> TypedDeclaration()<br>
	 * f1 -> <SEMICOLON><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final VarDeclaration n) {
		String typeName = n.f0.f0.accept(new TypeNameExtractor());
		String name = n.f0.f1.accept(new IdentifierNameExtractor());
		// TODO get rid of nasty cast
		this.table.put(
				name,
				new VariableEntry(name, (ClassEntry) this.table
						.lookup(typeName)));
		super.visit(n);
	}

	@Override
	public void visit(Expression n) {
		ExpressionVisitor expressionVisitor = new ExpressionVisitor(this.table);
		if (!expressionVisitor.check(n)) {
			throw new ExpressionCanNotBeEvaluatedException();
		}
		super.visit(n);
	}

	/**
	 * Visits a {@link If} node, whose children are the following :
	 * <p>
	 * f0 -> <IF><br>
	 * f1 -> <PARENTHESIS_LEFT><br>
	 * f2 -> Expression()<br>
	 * f3 -> <PARENTHESIS_RIGHT><br>
	 * f4 -> Statement()<br>
	 * f5 -> <ELSE><br>
	 * f6 -> Statement()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(If n) {
		this.requireExpressionType(n.f2, Types.BOOLEAN);
		super.visit(n);
	}

	/**
	 * Visits a {@link WhileLoop} node, whose children are the following :
	 * <p>
	 * f0 -> <WHILE><br>
	 * f1 -> <PARENTHESIS_LEFT><br>
	 * f2 -> Expression()<br>
	 * f3 -> <PARENTHESIS_RIGHT><br>
	 * f4 -> Statement()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(WhileLoop n) {
		this.requireExpressionType(n.f2, Types.BOOLEAN);
		super.visit(n);
	}

	/**
	 * Visits a {@link ExpressionPrime} node, whose child is the following :
	 * <p>
	 * f0 -> . %0 #0 BinaryOperator() #1 Expression() #2 ExpressionPrime()<br>
	 * .. .. | %1 #0 <BRACKET_LEFT> #1 Expression() #2 <BRACKET_RIGHT> #3
	 * ExpressionPrime()<br>
	 * .. .. | %2 #0 <DOT> #1 <LENGTH_FIELD_NAME> #2 ExpressionPrime()<br>
	 * .. .. | %3 MethodCall()<br>
	 * .. .. | %4 Empty()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(ExpressionPrime n) {
		if (n.f0.which == 1) {
			this.requireExpressionType(
					((NodeSequence) n.f0.choice).elementAt(1), Types.INT);
		}
		super.visit(n);
	}

	/**
	 * Visits a {@link Assignee} node, whose child is the following :
	 * <p>
	 * f0 -> . %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3
	 * <BRACKET_RIGHT><br>
	 * .. .. | %1 Identifier()<br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(Assignee n) {
		if (n.f0.which == 0) {
			this.requireExpressionType(
					((NodeSequence) n.f0.choice).elementAt(2), Types.INT);
		}
		super.visit(n);
	}

	public SymbolTable getTable() {
		return table;
	}

	protected void requireExpressionType(INode n, Types type) {
		ExpressionVisitor expressionVisitor = new ExpressionVisitor(this.table);
		if (!expressionVisitor.check(n)) {
			throw new ExpressionCanNotBeEvaluatedException();
		}
		if (this.table.lookup(expressionVisitor.expressionType) != this.table
				.lookup(type.getName())) {
			throw new ExpressionTypeException(type);
		}
	}

}
