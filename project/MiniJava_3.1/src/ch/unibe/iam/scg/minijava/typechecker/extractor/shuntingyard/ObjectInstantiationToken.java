package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.IntArrayConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.ObjectInstantiationExpression;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;

public class ObjectInstantiationToken extends
		AbstractFunctionToken<ObjectInstantiationExpression> {

	protected static class ObjectInstantiationVisitor extends
			DepthFirstVoidVisitor {

		protected String typeName;

		public String getTypeName() {
			return this.typeName;
		}

		@Override
		public void visit(ClassConstructorCall n) {
			this.typeName = n.f0.f0.tokenImage;
		}

		@Override
		public void visit(IntArrayConstructorCall n) {
			this.typeName = "int[]";
		}

	}

	public ObjectInstantiationToken(ObjectInstantiationExpression node) {
		super(node);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		ObjectInstantiationVisitor visitor = new ObjectInstantiationVisitor();
		this.node.accept(visitor);
		IType type = scope.lookupType(visitor.getTypeName());
		if (type instanceof ArrayType) {
			IType indexType = stack.pop().evaluate(scope, stack);
			if (!indexType.canBeAssignedTo(IntType.INSTANCE)) {
				throw new IncompatibleTypesException(indexType,
						IntType.INSTANCE);
			}
			return type;
		}
		return type;
	}

}
