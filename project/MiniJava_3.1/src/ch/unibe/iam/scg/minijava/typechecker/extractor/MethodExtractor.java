package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class MethodExtractor {

	protected static class MethodVisitor extends DepthFirstVoidVisitor {

		protected String name;
		protected String returnTypeName;
		protected List<ParameterDeclaration> parameterDeclarations;

		public String getName() {
			return name;
		}

		public String getReturnTypeName() {
			return returnTypeName;
		}

		public List<ParameterDeclaration> getParameterDeclarations() {
			return parameterDeclarations;
		}

		@Override
		public void visit(MethodDeclaration n) {
			this.name = (new IdentifierNameExtractor()).extract(n.f2);
			this.returnTypeName = (new TypeNameExtractor()).extract(n.f1);
			this.parameterDeclarations = new ArrayList<ParameterDeclaration>();
			super.visit(n);
		}

		@Override
		public void visit(ParameterDeclaration n) {
			this.parameterDeclarations.add(n);
		}

	}

	public Method extract(MethodDeclaration node, IScope scope)
			throws NameCollisionException, LookupException {
		MethodVisitor visitor = new MethodVisitor();
		node.accept(visitor);
		List<Variable> parameters = new ArrayList<Variable>();
		for (ParameterDeclaration parameterDeclaration : visitor
				.getParameterDeclarations()) {
			Variable parameter = (new VariableExtractor()).extract(
					parameterDeclaration, scope);
			parameters.add(parameter);
		}
		Method method = new Method(visitor.getName(), scope.lookupType(visitor
				.getReturnTypeName()), parameters);
		return method;
	}

}
