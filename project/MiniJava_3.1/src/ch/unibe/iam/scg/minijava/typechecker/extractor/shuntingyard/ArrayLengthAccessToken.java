package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayLengthAccess;
import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;
import ch.unibe.iam.scg.minijava.bcel.generator.CodeGeneratorVisitor;
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
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		IType objectType = stack.pop().evaluate(scope, stack);
		if (!(objectType instanceof ArrayType)) {
			throw new IncompatibleTypesException(objectType, new ArrayType(
					ObjectType.INSTANCE));
		}
		return IntType.INSTANCE;
	}

	@Override
	public void accept(CodeGeneratorVisitor vis) {
		vis.visit(this);
	}


}
