package ch.unibe.iam.scg.minijava.bcel.generator;

import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class MethodCallVisitor extends DepthFirstVoidVisitor {
	private boolean inCall;

	public boolean check(Expression n) {
		n.accept(this);
		return inCall;
	}
	
	public void visit(final MethodCall n) {
		inCall = true; 
		super.visit(n);
	}

}
