package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;

public class MethodEntryBuilder extends SymbolTableBuilder<MethodEntry> {

	public MethodEntryBuilder(MethodEntry methodEntry) {
		super(methodEntry);
	}

	/**
	 * Visits a {@link MethodDeclaration} node, whose children are the following
	 * :
	 * <p>
	 * f0 -> <PUBLIC_MODIFIER><br>
	 * f1 -> TypedDeclaration()<br>
	 * f2 -> <PARENTHESIS_LEFT><br>
	 * f3 -> ParameterDeclarationList()<br>
	 * f4 -> <PARENTHESIS_RIGHT><br>
	 * f5 -> <BRACE_LEFT><br>
	 * f6 -> ( VarDeclaration() )*<br>
	 * f7 -> ( Statement() )*<br>
	 * f8 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?<br>
	 * f9 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(MethodDeclaration n) {
		if (!n.f8.present() && this.table.getReturnType() != this.table.lookup(Types.VOID.getName())){
			throw new ReturnValueFromVoidMethodException();
		}
		if (n.f8.present()){
			Expression expression = n.f8.accept(new FirstExpressionExtractor());
			ExpressionVisitor expressionVisitor = new ExpressionVisitor(this.table);
			expressionVisitor.check(expression);
			// TODO get rid of cast
			ClassEntry returnedType =  (ClassEntry) this.table.lookup(expressionVisitor.expressionType);
			if (!returnedType.canBeAssignedTo(this.table.getReturnType())){
				throw new ReturnedTypeCanNotBeAssignedToReturnTypeException(returnedType, this.table.getReturnType());
			}
		}
		super.visit(n);
	}

}
