package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.javacc.syntaxtree.Parameter;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.IncompatibleTypesException;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;

public class MethodCallToken extends AbstractFunctionToken<MethodCall> {

	protected static class ParameterVisitor extends DepthFirstVoidVisitor {

		protected int count;

		public ParameterVisitor() {
			super();
			this.count = 0;
		}

		public int getCount() {
			return this.count;
		}

		@Override
		public void visit(Parameter n) {
			this.count = this.count + 1;
		}

	}

	public MethodCallToken(MethodCall node) {
		super(node);
	}

	@Override
	public IType evaluate(IScope scope, Stack<IToken> stack) {
		ParameterVisitor visitor = new ParameterVisitor();
		this.node.accept(visitor);
		int parameterCount = visitor.getCount();
		List<IType> parameterTypes = new ArrayList<IType>();
		while (parameterCount > 0) {
			parameterTypes.add(0, stack.pop().evaluate(scope, stack));
			parameterCount = parameterCount - 1;
		}
		String methodName = this.node.f1.f0.tokenImage;
		IType type = stack.pop().evaluate(scope, stack);
		Method method = scope.lookupTypeScope(type.getName()).lookupMethod(
				methodName);
		if (method.getParameters().size() != parameterTypes.size()) {
			throw new WrongNumberOfArgumentException(parameterTypes.size(),
					method.getParameters().size());
		}
		for (int i = 0; i < parameterTypes.size(); i = i + 1) {
			IType expectedType = method.getParameters().get(i).getType();
			IType actualType = parameterTypes.get(i);
			if (!actualType.canBeAssignedTo(expectedType)) {
				throw new IncompatibleTypesException(actualType, expectedType);
			}
		}
		return method.getReturnType();
	}
}
