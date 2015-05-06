package ch.unibe.iam.scg.minijava.typechecker.extractor;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class IdentifierNameExtractor extends DepthFirstVoidVisitor {

	public static String extractFrom(INode node) {
		IdentifierNameExtractor extractor = new IdentifierNameExtractor();
		node.accept(extractor);
		return extractor.getIdentifier();
	}

	protected String identifier;

	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public void visit(Identifier n) {
		this.identifier = n.f0.tokenImage;
	}

}
