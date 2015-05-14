package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayAccess;
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
	public IType evaluate(IScope scope, IType... parameterTypes) {
		if (parameterTypes.length != 2) {
			throw new WrongNumberOfArgumentException(parameterTypes.length, 2);
		}
		if (!(parameterTypes[0] instanceof ArrayType)) {
			throw new IncompatibleTypesException(parameterTypes[0],
					new ArrayType(ObjectType.INSTANCE));
		}
		if (!parameterTypes[1].canBeAssignedTo(IntType.INSTANCE)) {
			throw new IncompatibleTypesException(parameterTypes[1],
					IntType.INSTANCE);
		}
		return ((ArrayType) parameterTypes[0]).getElementType();
	}

}
