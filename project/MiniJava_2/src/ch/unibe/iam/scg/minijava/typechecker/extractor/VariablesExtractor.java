package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class VariablesExtractor {

	protected static class TypedDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected List<TypedDeclaration> typedDeclarations;

		public TypedDeclarationVisitor() {
			super();
			this.typedDeclarations = new ArrayList<TypedDeclaration>();
		}

		public List<TypedDeclaration> getTypedDeclarations() {
			return typedDeclarations;
		}

		@Override
		public void visit(TypedDeclaration n) {
			this.typedDeclarations.add(n);
		}

	}

	public Map<String, Variable> extract(INode node, IScope scope)
			throws NameCollisionException, LookupException {
		TypedDeclarationVisitor visitor = new TypedDeclarationVisitor();
		node.accept(visitor);
		List<TypedDeclaration> typedDeclarations = visitor
				.getTypedDeclarations();
		Map<String, Variable> variables = new HashMap<String, Variable>();
		for (TypedDeclaration typedDeclaration : typedDeclarations) {
			Variable variable = (new VariableExtractor()).extract(
					typedDeclaration, scope);
			if (variables.containsKey(variable.getName())) {
				throw new NameCollisionException(variable.getName());
			}
			variables.put(variable.getName(), variable);
		}
		return variables;
	}

}
