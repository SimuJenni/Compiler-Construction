package ch.unibe.iam.scg.minijava.typechecker.evaluator;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.ArrayAccess;
import ch.unibe.iam.scg.javacc.syntaxtree.AssignmentStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.IfStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.MainClass;
import ch.unibe.iam.scg.javacc.syntaxtree.MainMethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileStatement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.ExpressionTypeExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

public class Evaluator {

	protected static class EvaluatingVisitor extends
			DepthFirstVoidArguVisitor<IScope> {

		protected Map<INode, IScope> scopeMap;

		public EvaluatingVisitor(Map<INode, IScope> scopeMap) {
			super();
			this.scopeMap = scopeMap;
		}

		@Override
		public void visit(MainClass n, IScope parent) {
			super.visit(n, this.scopeMap.get(n));
		}

		@Override
		public void visit(MainMethodDeclaration n, IScope parent) {
			super.visit(n, this.scopeMap.get(n));
		}

		@Override
		public void visit(ClassDeclaration n, IScope parent) {
			super.visit(n, this.scopeMap.get(n));
		}

		@Override
		public void visit(MethodDeclaration n, IScope parent) {
			IScope scope = this.scopeMap.get(n);
			Method method = scope.lookupMethod(n.f2.f0.tokenImage);
			IType returnType = method.getReturnType();
			if (n.f9.present()) {
				if (returnType == VoidType.INSTANCE) {
					throw new ReturnValueFromVoidMethodException();
				}
				IType returnedType = (new ExpressionTypeExtractor()).extract(
						n.f9, scope);
				if (!returnedType.canBeAssignedTo(returnType)) {
					throw new IncompatibleTypesException(returnedType,
							returnType);
				}
			} else {
				if (returnType != VoidType.INSTANCE) {
					throw new MissingReturnValueFromNonVoidMethodException();
				}
			}
			n.f8.accept(this, scope);
		}

		@Override
		public void visit(IfStatement n, IScope scope) {
			IType returnedType = (new ExpressionTypeExtractor()).extract(n.f2,
					scope);
			if (!returnedType.canBeAssignedTo(BooleanType.INSTANCE)) {
				throw new IncompatibleTypesException(returnedType,
						BooleanType.INSTANCE);
			}
			n.f4.accept(this, scope);
			n.f6.accept(this, scope);
		}

		@Override
		public void visit(WhileStatement n, IScope scope) {
			IType returnedType = (new ExpressionTypeExtractor()).extract(n.f2,
					scope);
			if (!returnedType.canBeAssignedTo(BooleanType.INSTANCE)) {
				throw new IncompatibleTypesException(returnedType,
						BooleanType.INSTANCE);
			}
			n.f4.accept(this, scope);
		}

		@Override
		public void visit(AssignmentStatement n, IScope scope) {
			IType actualType = (new ExpressionTypeExtractor()).extract(n.f2,
					scope);
			String name = (new IdentifierNameExtractor())
					.extract(n.f0.f0.choice);
			IType expectedType = scope.lookupVariable(name).getType();
			if (n.f0.f0.which == 0) {
				expectedType = ((ArrayType) expectedType).getElementType();
				n.f0.f0.choice.accept(this, scope);
			}
			if (!actualType.canBeAssignedTo(expectedType)) {
				throw new IncompatibleTypesException(actualType, expectedType);
			}
		}

		@Override
		public void visit(ArrayAccess n, IScope scope) {
			IType actualType = (new ExpressionTypeExtractor()).extract(n.f1,
					scope);
			if (!actualType.canBeAssignedTo(IntType.INSTANCE)) {
				throw new IncompatibleTypesException(actualType,
						IntType.INSTANCE);
			}
		}

		@Override
		public void visit(Expression n, IScope scope) {
			(new ExpressionTypeExtractor()).extract(n, scope);
		}

	}

	protected Map<INode, IScope> scopeMap;

	public Evaluator(Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
	}

	public void evaluate(INode n) throws LookupException,
			IncompatibleTypesException {
		EvaluatingVisitor visitor = new EvaluatingVisitor(this.scopeMap);
		n.accept(visitor, this.scopeMap.get(n));
	}

}
