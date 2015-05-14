package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
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
			while (!tokens.isEmpty()) {
				IToken token = tokens.remove(0);
				if (token.isLiteral() && tokens.isEmpty()) {
					this.type = token.evaluate(scope);
					break;
				}
				List<IType> parameterTypes = new ArrayList<IType>();
				while (token.isLiteral()) {
					parameterTypes.add(token.evaluate(scope));
					token = tokens.remove(0);
				}
				IType returnedType = token.evaluate(scope,
						parameterTypes.toArray(new IType[0]));
				tokens.add(0, new LiteralToken(returnedType));
			}
		}

	}

	public IType extract(INode node, IScope scope) {
		ExpressionVisitor visitor = new ExpressionVisitor();
		node.accept(visitor, scope);
		return visitor.getType();
	}

}
