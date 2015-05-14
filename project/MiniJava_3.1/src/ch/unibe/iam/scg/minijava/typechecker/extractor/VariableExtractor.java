package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class VariableExtractor {

	protected static class TypedDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected String typeName;
		protected String name;

		public String getTypeName() {
			return typeName;
		}

		public String getName() {
			return name;
		}

		@Override
		public void visit(TypedDeclaration n) {
			this.typeName = (new TypeNameExtractor()).extract(n.f0);
			this.name = (new IdentifierNameExtractor()).extract(n.f1);
		}

	}

	public Variable extract(INode node, IScope scope) throws LookupException {
		TypedDeclarationVisitor visitor = new TypedDeclarationVisitor();
		node.accept(visitor);
		return new Variable(visitor.getName(), scope.lookupType(visitor
				.getTypeName()));
	}

}
