package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class NodeTokenConcatenatingExtractor {

	protected class NodeTokenVisitor extends DepthFirstVoidVisitor {

		protected String image;

		public NodeTokenVisitor() {
			super();
			this.image = "";
		}

		public String getImage() {
			return this.image;
		}

		@Override
		public void visit(NodeToken n) {
			this.image = this.image + n.tokenImage;
		}

	}

	public String extract(INode node) {
		NodeTokenVisitor visitor = new NodeTokenVisitor();
		node.accept(visitor);
		return visitor.getImage();
	}

}
