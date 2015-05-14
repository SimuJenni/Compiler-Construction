package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayLengthAccess;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.ObjectType;

public class ArrayLengthAccessToken extends AbstractFunctionToken<ArrayLengthAccess> {

	public ArrayLengthAccessToken(ArrayLengthAccess node) {
		super(node);
	}

	@Override
	public IType evaluate(IScope scope, IType... parameterTypes) {
		if (parameterTypes.length != 1) {
			throw new WrongNumberOfArgumentException(parameterTypes.length, 1);
		}
		if (!(parameterTypes[0] instanceof ArrayType)) {
			throw new IncompatibleTypesException(parameterTypes[0],
					new ArrayType(ObjectType.INSTANCE));
		}
		return IntType.INSTANCE;
	}

}
