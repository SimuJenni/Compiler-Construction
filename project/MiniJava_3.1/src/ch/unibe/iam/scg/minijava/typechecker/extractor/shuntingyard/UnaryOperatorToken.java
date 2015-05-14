package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class UnaryOperatorToken extends AbstractOperatorToken {

	public UnaryOperatorToken(UnaryOperator node) {
		super(node.f0.tokenImage, Associativity.RIGHT);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		IType operandType = stack.pop().evaluate(scope, stack);
		if (!operandType.canBeAssignedTo(this.returnType)) {
			throw new IncompatibleTypesException(operandType, this.returnType);
		}
		return this.returnType;
	}

}
