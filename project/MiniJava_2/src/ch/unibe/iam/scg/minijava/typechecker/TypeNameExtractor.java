package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class TypeNameExtractor extends DepthFirstRetVisitor<String> {

	/**
	 * Visits a {@link Type} node, whose child is the following :
	 * <p>
	 * f0 -> . %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT><br>
	 * .. .. | %1 <INT_TYPE><br>
	 * .. .. | %2 BooleanType()<br>
	 * .. .. | %3 <VOID_TYPE><br>
	 * .. .. | %4 Identifier()<br>
	 *
	 * @param n
	 *            - the node to visit
	 * @return the user return information
	 */
	@Override
	public String visit(final Type n) {
		final NodeChoice nch = n.f0;
		final INode ich = n.f0.choice;
		switch (nch.which) {
		case 0:
			return Types.INT_ARRAY.toString();
		case 1:
			return Types.INT.toString();
		case 2:
			return Types.BOOLEAN.toString();
		case 3:
			return Types.VOID.toString();
		case 4:
			return ich.accept(new IdentifierNameExtractor());
		default:
			throw new RuntimeException("Unknown type");
		}
	}

}
