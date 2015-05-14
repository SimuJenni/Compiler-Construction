package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class TypeNameExtractor {

	protected class TypeVisitor extends DepthFirstVoidVisitor {

		protected String typeName;

		public String getTypeName() {
			return this.typeName;
		}

		@Override
		public void visit(Type n) {
			this.typeName = (new NodeTokenConcatenatingExtractor())
					.extract(n.f0.choice);
		}

	}

	public String extract(INode node) {
		TypeVisitor visitor = new TypeVisitor();
		node.accept(visitor);
		return visitor.getTypeName();
	}

}
