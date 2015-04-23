package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstRetVisitor;

public class OperatorArgVisitor extends DepthFirstRetVisitor<String> {

	  /**
	   * Visits a {@link BinaryOperator} node, whose child is the following :
	   * <p>
	   * f0 -> <BINARY_OPERATOR><br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public String visit(final BinaryOperator n) {
	    // f0 -> <BINARY_OPERATOR>
		String s=n.f0.tokenImage;
		if(s.equals("&&"))
			return Types.BOOLEAN.getName();
		else
			return Types.INT.getName();
	  }
}
