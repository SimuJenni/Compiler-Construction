package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class VariableExtractor {

	public static class VariableVisitor extends DepthFirstVoidVisitor {

		protected String typeName;
		protected String identifierName;

		public String getTypeName() {
			return typeName;
		}

		public String getIdentifierName() {
			return identifierName;
		}

		@Override
		public void visit(TypedDeclaration n) {
			this.typeName = (new TypeNameExtractor()).extract(n.f0);
			this.identifierName = (new IdentifierNameExtractor()).extract(n.f1);
		}

	}

	public Variable extract(INode node, IScope scope) throws LookupException {
		VariableVisitor visitor = new VariableVisitor();
		node.accept(visitor);
		return new Variable(visitor.getIdentifierName(),
				scope.lookupType(visitor.getTypeName()));
	}

}
