package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Iterator;

import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class FirstExpressionExtractor extends DepthFirstRetVisitor<Expression> {

	/**
	 * Visits a {@link NodeSequence} node.
	 *
	 * @param n
	 *            - the node to visit
	 * @return the user return information
	 */
	@Override
	public Expression visit(final NodeSequence n) {
		/*
		 * You have to adapt which data is returned (result variables below are
		 * just examples)
		 */
		Expression nRes = null;
		for (final Iterator<INode> e = n.elements(); e.hasNext();) {
			Expression subRet = e.next().accept(this);
			if (subRet != null) {
				nRes = subRet;
			}
		}
		return nRes;
	}
	
	@Override
	public Expression visit(Expression n) {
		return n;
	}

}
