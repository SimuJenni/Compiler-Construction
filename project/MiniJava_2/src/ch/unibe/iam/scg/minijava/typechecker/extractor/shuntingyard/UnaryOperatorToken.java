package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class UnaryOperatorToken extends AbstractOperatorToken {

	public UnaryOperatorToken(UnaryOperator node) {
		super(node.f0.tokenImage, Associativity.RIGHT);
	}

	@Override
	public IType evaluate(IScope scope, IType... parameterTypes) {
		if (parameterTypes.length != 1) {
			throw new WrongNumberOfArgumentException(parameterTypes.length, 1);
		}
		if (!parameterTypes[0].canBeAssignedTo(this.returnType)) {
			throw new IncompatibleTypesException(parameterTypes[0], this.returnType);
		}
		return this.returnType;
	}

}
