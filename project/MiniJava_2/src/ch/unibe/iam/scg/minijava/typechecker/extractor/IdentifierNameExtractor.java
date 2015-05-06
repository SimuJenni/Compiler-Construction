package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class IdentifierNameExtractor {

	protected class IdentifierVisitor extends DepthFirstVoidVisitor {

		protected String identifierName;

		public String getIdentifierName() {
			return this.identifierName;
		}

		@Override
		public void visit(Identifier n) {
			this.identifierName = n.f0.tokenImage;
		}
		
	}

	public String extract(INode node) {
		IdentifierVisitor visitor = new IdentifierVisitor();
		node.accept(visitor);
		return visitor.getIdentifierName();
	}

}
