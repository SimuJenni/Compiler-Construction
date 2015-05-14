package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.List;
import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class ExpressionTypeExtractor {

	public class ExpressionVisitor extends DepthFirstVoidArguVisitor<IScope> {

		protected IType type;

		public IType getType() {
			return this.type;
		}

		@Override
		public void visit(Expression n, IScope scope) {
			List<IToken> tokens = (new ShuntingYard(scope)).extract(n);
			Stack<IToken> stack = new Stack<IToken>();
			for (IToken token : tokens) {
				stack.push(token);
			}
			this.type = stack.pop().evaluate(scope, stack);
		}

	}

	public IType extract(INode node, IScope scope) {
		ExpressionVisitor visitor = new ExpressionVisitor();
		node.accept(visitor, scope);
		return visitor.getType();
	}

}
