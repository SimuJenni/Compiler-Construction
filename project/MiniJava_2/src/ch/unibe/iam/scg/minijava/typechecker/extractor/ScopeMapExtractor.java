package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.GlobalScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class ScopeMapExtractor {

	protected static class DeclarationVisitor extends
			DepthFirstVoidArguVisitor<IScope> {

		protected ScopeMap scopeMap;

		public DeclarationVisitor(ScopeMap scopeMap) {
			super();
			this.scopeMap = scopeMap;
		}

		@Override
		public void visit(ClassDeclaration n, IScope parent) {
			String className = n.f1.f0.tokenImage;
			IType type = parent.lookupType(className);
			IScope child = type.getScope();
			super.visit(n, child);
		}

		@Override
		public void visit(MethodDeclaration n, IScope parent) {
			Method method = (new MethodExtractor()).extract(n, parent);
			IScope child = method.getScope();
			child.setParent(parent);
			parent.putMethod(method.getName(), method);
			super.visit(n, child);
		}

		@Override
		public void visit(VarDeclaration n, IScope parent) {
			Variable variable = (new VariableExtractor()).extract(n, parent);
			parent.putVariable(variable.getName(), variable);
			super.visit(n, parent);
		}

	}

	public ScopeMap extract(INode node, GlobalScope globalScope) {
		ScopeMap scopeMap = new ScopeMap();
		node.accept(new DeclarationVisitor(scopeMap), globalScope);
		return scopeMap;
	}

}
