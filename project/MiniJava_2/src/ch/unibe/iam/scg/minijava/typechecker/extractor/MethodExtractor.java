package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class MethodExtractor {

	protected static class MethodVisitor extends DepthFirstVoidVisitor {

		protected String name;
		protected String returnTypeName;
		protected INode parameterDeclarations;
		protected INode variableDeclarations;

		public String getName() {
			return name;
		}

		public String getReturnTypeName() {
			return returnTypeName;
		}

		public INode getParameterDeclarations() {
			return parameterDeclarations;
		}

		public INode getVariableDeclarations() {
			return variableDeclarations;
		}

		@Override
		public void visit(MethodDeclaration n) {
			this.name = (new IdentifierNameExtractor()).extract(n.f2);
			this.returnTypeName = (new TypeNameExtractor()).extract(n.f1);
			this.parameterDeclarations = n.f3;
			this.variableDeclarations = n.f6;
		}

	}

	public Method extract(INode node, IType type, IScope scope)
			throws NameCollisionException, LookupException {
		MethodVisitor visitor = new MethodVisitor();
		node.accept(visitor);
		Method method = new Method(type, visitor.getName(),
				scope.lookupType(visitor.getReturnTypeName()));
		for (Map.Entry<String, Variable> entry : (new VariablesExtractor())
				.extract(visitor.getParameterDeclarations(), scope).entrySet()) {
			method.putParameter(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, Variable> entry : (new VariablesExtractor())
				.extract(visitor.getVariableDeclarations(), scope).entrySet()) {
			method.putVariable(entry.getKey(), entry.getValue());
		}
		return method;
	}

}
