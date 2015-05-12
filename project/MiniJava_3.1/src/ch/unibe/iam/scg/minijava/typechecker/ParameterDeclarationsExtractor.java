package ch.unibe.iam.scg.minijava.typechecker;

import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidArguVisitor;

public class ParameterDeclarationsExtractor extends
		DepthFirstVoidArguVisitor<List<ParameterDeclaration>> {

	@Override
	public void visit(ParameterDeclaration n,
			List<ParameterDeclaration> parameterDeclarations) {
		parameterDeclarations.add(n);
		super.visit(n, parameterDeclarations);
	}

}
