package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;

public class MethodsExtractor {

	protected static class MethodDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected List<MethodDeclaration> methodDeclarations;

		public MethodDeclarationVisitor() {
			super();
			this.methodDeclarations = new ArrayList<MethodDeclaration>();
		}

		public List<MethodDeclaration> getMethodDeclarations() {
			return methodDeclarations;
		}

		@Override
		public void visit(MethodDeclaration n) {
			this.methodDeclarations.add(n);
		}

	}

	public Map<String, Method> extract(INode node, IType type, IScope scope)
			throws NameCollisionException, LookupException {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		node.accept(visitor);
		List<MethodDeclaration> methodDeclarations = visitor
				.getMethodDeclarations();
		Map<String, Method> methods = new HashMap<String, Method>();
		for (MethodDeclaration methodDeclaration : methodDeclarations) {
			Method method = (new MethodExtractor()).extract(methodDeclaration,
					type, scope);
			if (methods.containsKey(method.getName())) {
				throw new NameCollisionException(method.getName());
			}
			methods.put(method.getName(), method);
		}
		return methods;
	}

}
