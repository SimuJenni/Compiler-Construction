package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayAccess;
import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;
import ch.unibe.iam.scg.minijava.bcel.generator.CodeGeneratorVisitor;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.ObjectType;

public class ArrayAccessToken extends AbstractFunctionToken<ArrayAccess> {

	public ArrayAccessToken(ArrayAccess node) {
		super(node);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		IType indexType = stack.pop().evaluate(scope, stack);
		IType objectType = stack.pop().evaluate(scope, stack);
		if (!indexType.canBeAssignedTo(IntType.INSTANCE)) {
			throw new IncompatibleTypesException(indexType, IntType.INSTANCE);
		}
		if (!(objectType instanceof ArrayType)) {
			throw new IncompatibleTypesException(objectType, new ArrayType(
					ObjectType.INSTANCE));
		}
		return ((ArrayType) objectType).getElementType();
	}

	@Override
	public void accept(CodeGeneratorVisitor vis) {
		// TODO Auto-generated method stub
		
	}

}
