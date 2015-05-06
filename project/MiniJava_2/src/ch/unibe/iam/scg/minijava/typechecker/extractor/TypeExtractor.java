package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class TypeExtractor {

	protected static class ClassDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected INode variableDeclarations;
		protected INode methodDeclarations;

		@Override
		public void visit(ClassDeclaration n) {
			this.variableDeclarations = n.f4;
			this.methodDeclarations = n.f5;
		}

		public INode getVariableDeclarations() {
			return variableDeclarations;
		}

		public INode getMethodDeclarations() {
			return methodDeclarations;
		}

	}

	public IType extract(INode node, IType type, IScope scope)
			throws NameCollisionException, LookupException {
		ClassDeclarationVisitor visitor = new ClassDeclarationVisitor();
		node.accept(visitor);
		for (Map.Entry<String, Variable> entry : (new VariablesExtractor())
				.extract(visitor.getVariableDeclarations(), scope).entrySet()) {
			type.putVariable(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, Method> entry : (new MethodsExtractor())
				.extract(visitor.getMethodDeclarations(), type, scope)
				.entrySet()) {
			type.putMethod(entry.getKey(), entry.getValue());
		}
		return type;
	}

}
