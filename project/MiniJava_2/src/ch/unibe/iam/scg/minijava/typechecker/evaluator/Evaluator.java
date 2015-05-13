package ch.unibe.iam.scg.minijava.typechecker.evaluator;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.ExpressionTypeExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

public class Evaluator {

	protected static class MethodVisitor extends
			DepthFirstVoidArguVisitor<IScope> {

		protected Map<INode, IScope> scopeMap;

		public MethodVisitor(Map<INode, IScope> scopeMap) {
			super();
			this.scopeMap = scopeMap;
		}

		@Override
		public void visit(MethodDeclaration n, IScope parent) {
			Method method = parent.lookupMethod(n.f2.f0.tokenImage);
			IType returnType = method.getReturnType();
			IScope child = this.scopeMap.get(n);
			if (n.f9.present()) {
				if (returnType == VoidType.INSTANCE) {
					throw new ReturnValueFromVoidMethodException();
				}
				IType returnedType = (new ExpressionTypeExtractor()).extract(
						n.f9, child);
				if (!returnedType.canBeAssignedTo(returnType)) {
					throw new ReturnedTypeCanNotBeAssignedToReturnTypeException(
							returnedType, returnType);
				}
			} else {
				if (returnType != VoidType.INSTANCE) {
					throw new MissingReturnValueFromNonVoidMethodException();
				}
			}
			super.visit(n, child);
		}

	}

	protected Map<INode, IScope> scopeMap;

	public Evaluator(Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
	}

	public void evaluate(INode n) throws LookupException,
			IncompatibleTypesException {
		MethodVisitor visitor = new MethodVisitor(this.scopeMap);
		n.accept(visitor, this.scopeMap.get(n));
	}

}
