package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.MiniJavaImplConstants;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class ExpressionVisitor extends DepthFirstVoidVisitor implements
		MiniJavaImplConstants {
	private SymbolTable table;
	public String expressionType;
	private boolean valid;
	private Stack<String> typeStack = new Stack<String>();

	public ExpressionVisitor(SymbolTable table) {
		this.table = table;
		valid = true;
	}

	public boolean check(Object node) {
		INode n = (INode) node;
		n.accept(this);
		if (!typeStack.isEmpty())
			expressionType = typeStack.pop();
		return valid;
	}

	@Override
	public void visit(final Expression n) {
		ShuntingYard sy = new ShuntingYard();
		n.accept(sy);
		sy.flushStack();

		for (INode node : sy.output) {
			node.accept(this);
		}
	}

	/**
	 * Visits a {@link NodeToken} node.
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final NodeToken n) {
		String tokenType = tokenImage[n.kind];
		String type = null;
		if (tokenType.equals("<INTEGER_LITERAL>"))
			type = Types.INT.getName();
		if (tokenType.equals("\"true\""))
			type = Types.BOOLEAN.getName();
		if (tokenType.equals("\"false\""))
			type = Types.BOOLEAN.getName();
		if (tokenType.equals("\"this\""))
			type = "this";

		typeStack.push(type);
		expressionType = type;
		String tkIm = n.tokenImage;
	}

	/**
	 * IntType means we're dealing with an array
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final IntType n) {
		typeStack.push(Types.INT_ARRAY.getName());
	}

	public void visit(Operator operator) {
		if (operator.isBracket()) {
			String argType = operator.getArgType();
			checkSameType(argType, typeStack.pop());
			checkSameType(Types.INT_ARRAY.getName(), typeStack.pop());
			if (((ClassEntry) table.lookup(Types.INT_ARRAY.getName()))
					.isInitialized())
				typeStack.push(Types.INT.getName());
			else
				typeStack.push(Types.INT_ARRAY.getName());
			return;
		}

		if (operator.isLeftParanthesis() || operator.isRightParanthesis())
			return;

		if (operator.isUnary()) {
			String argType = operator.getArgType();
			checkSameType(argType, typeStack.pop());
			expressionType = operator.getReturnType();
			typeStack.push(expressionType);
			return;
		}

		if (operator.isDot()) {
			String methodName = typeStack.pop();
			String className = typeStack.pop();

			ClassEntry clEntry = (ClassEntry) this.table.lookup(className);
			MethodEntry mEntry = (MethodEntry) clEntry.lookup(methodName);

			typeStack.push(mEntry.getReturnType().getName());
			return;

		}

		if (operator.isBinary()) {
			String argType = operator.getArgType();
			checkSameType(argType, typeStack.pop());
			checkSameType(argType, typeStack.pop());
			expressionType = operator.getReturnType();
			typeStack.push(expressionType);
			return;
		}
	}

	/**
	 * Visits a {@link Identifier} node, whose child is the following :
	 * <p>
	 * f0 -> <IDENTIFIER><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(final Identifier n) {
		// f0 -> <IDENTIFIER>
		final NodeToken n0 = n.f0;
		try {
			SymbolTableEntry id = this.table.lookup(n0.tokenImage);
			typeStack.push(id.getEntryTypeName());
		} catch (SymbolNotFoundException e) {
			typeStack.push(n0.tokenImage);
		}
	}

	private String checkSameType(String type1, String type2) {
		if (type1 == null)
			return type2;
		if (type2 == null)
			return type1;
		if (type1.equals(type2))
			return type1;
		else {
			valid = false;
			return null;
		}
	}

}
