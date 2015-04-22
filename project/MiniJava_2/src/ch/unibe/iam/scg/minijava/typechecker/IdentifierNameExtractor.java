package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Iterator;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class IdentifierNameExtractor extends DepthFirstRetVisitor<String> {

	/**
	 * Visits a {@link NodeSequence} node.
	 *
	 * @param n
	 *            - the node to visit
	 * @return the user return information
	 */
	@Override
	public String visit(final NodeSequence n) {
		/*
		 * You have to adapt which data is returned (result variables below are
		 * just examples)
		 */
		String nRes = null;
		for (final Iterator<INode> e = n.elements(); e.hasNext();) {
			String subRet = e.next().accept(this);
			if (subRet != null) {
				nRes = subRet;
			}
		}
		return nRes;
	}

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
