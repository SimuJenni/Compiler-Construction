package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class IdentifierNameExtractor extends DepthFirstRetVisitor<String> {

	/**
	 * Visits a {@link Identifier} node, whose child is the following :
	 * <p>
	 * f0 -> <IDENTIFIER><br>
	 *
	 * @param n
	 *            - the node to visit
	 * @return the user return information
	 */
	@Override
	public String visit(Identifier n) {
		return n.f0.tokenImage;
	}

}
