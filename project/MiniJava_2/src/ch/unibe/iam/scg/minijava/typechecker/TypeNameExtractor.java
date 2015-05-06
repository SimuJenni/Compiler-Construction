package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;

public class TypeNameExtractor extends DepthFirstRetVisitor<String> {

	/**
	 * Visits a {@link Type} node, whose child is the following :
	 * <p>
	 * f0 -> . %0 IntArrayType()<br>
	 * .. .. | %1 IntType()<br>
	 * .. .. | %2 BooleanType()<br>
	 * .. .. | %3 VoidType()<br>
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
			return Types.INT_ARRAY.getName();
		case 1:
			return Types.INT.getName();
		case 2:
			return Types.BOOLEAN.getName();
		case 3:
			return Types.VOID.getName();
		case 4:
			return ich.accept(new IdentifierNameExtractor());
		default:
			throw new RuntimeException("Unknown type");
		}
	}

}
