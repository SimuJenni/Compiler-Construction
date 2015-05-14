package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class BinaryOperatorToken extends AbstractOperatorToken {

	public BinaryOperatorToken(BinaryOperator node) {
		super(node.f0.tokenImage, Associativity.LEFT);
	}

	@Override
	public IType evaluate(IScope scope, IType... parameterTypes) {
		if (parameterTypes.length != 2) {
			throw new WrongNumberOfArgumentException(parameterTypes.length, 2);
		}
		if (!parameterTypes[0].canBeAssignedTo(this.returnType)) {
			throw new IncompatibleTypesException(parameterTypes[0], this.returnType);
		}
		if (!parameterTypes[1].canBeAssignedTo(this.returnType)) {
			throw new IncompatibleTypesException(parameterTypes[1], this.returnType);
		}
		return this.returnType;
	}

}
