package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.HashMap;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.Scope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class ScopeMapExtractor {

	protected static class DeclarationVisitor extends
			DepthFirstVoidArguVisitor<IScope> {

		protected Map<INode, IScope> scopeMap;

		public DeclarationVisitor() {
			super();
			this.scopeMap = new HashMap<INode, IScope>();
		}

		public Map<INode, IScope> getScopeMap() {
			return this.scopeMap;
		}

		@Override
		public void visit(ClassDeclaration n, IScope parent) {
			String className = n.f1.f0.tokenImage;
			IType type = parent.lookupType(className);
			IScope child = parent.lookupTypeScope(className);
			Variable thisVariable = new Variable("this", type);
			child.putVariable(thisVariable.getName(), thisVariable);
			this.scopeMap.put(n, child);
			super.visit(n, child);
		}

		@Override
		public void visit(MethodDeclaration n, IScope parent) {
			Method method = (new MethodExtractor()).extract(n, parent);
			parent.putMethod(method.getName(), method);
			IScope child = new Scope(parent);
			for (Variable parameter : method.getParameters()) {
				child.putVariable(parameter.getName(), parameter);
			}
			this.scopeMap.put(n, child);
			super.visit(n, child);
		}

		@Override
		public void visit(VarDeclaration n, IScope parent) {
			Variable variable = (new VariableExtractor()).extract(n, parent);
			parent.putVariable(variable.getName(), variable);
			super.visit(n, parent);
		}

	}

	public Map<INode, IScope> extract(INode node, IScope scope) {
		DeclarationVisitor visitor = new DeclarationVisitor();
		Map<INode, IScope> scopeMap = visitor.getScopeMap();
		scopeMap.put(node, scope);
		node.accept(visitor, scope);
		return scopeMap;
	}

}
