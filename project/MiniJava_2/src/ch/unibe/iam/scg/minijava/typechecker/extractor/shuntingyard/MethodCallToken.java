package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class MethodCallToken extends AbstractFunctionToken<MethodCall> {

	public MethodCallToken(MethodCall node) {
		super(node);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		return null;
		// TODO fix scope
//		Method method = scope.getClassScope(parameterTypes[0]).lookupMethod(
//				this.node.f1.f0.tokenImage);
//		if (method.getParameters().size() != parameterTypes.length - 1) {
//			throw new WrongNumberOfArgumentException(parameterTypes.length - 1,
//					method.getParameters().size());
//		}
//		for (int i = 0; i < parameterTypes.length - 1; i = i + 1) {
//			IType expectedType = method.getParameters().get(i).getType();
//			IType actualType = parameterTypes[i + 1];
//			if (!actualType.canBeAssignedTo(expectedType)) {
//				throw new IncompatibleTypesException(actualType, expectedType);
//			}
//		}
//		return method.getReturnType();
	}

}
