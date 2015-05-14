package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class BinaryOperatorToken extends AbstractOperatorToken {

	public BinaryOperatorToken(BinaryOperator node) {
		super(node.f0.tokenImage, Associativity.LEFT);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		IType operandType1 = stack.pop().evaluate(scope, stack);
		IType operandType2 = stack.pop().evaluate(scope, stack);
		if (!operandType1.canBeAssignedTo(this.parameterType)) {
			throw new IncompatibleTypesException(operandType1, this.parameterType);
		}
		if (!operandType2.canBeAssignedTo(this.parameterType)) {
			throw new IncompatibleTypesException(operandType2, this.parameterType);
		}
		return this.returnType;
	}

}
