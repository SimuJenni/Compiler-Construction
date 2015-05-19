package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.MainMethodDeclaration;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

public class MainMethodExtractor {

	public Method extract(MainMethodDeclaration n, IScope scope) {
		List<Variable> parameters = new ArrayList<Variable>();
		Variable parameter = new Variable(n.f8.f0.tokenImage,
				scope.lookupType("String[]"));
		parameters.add(parameter);
		Method method = new Method("main", VoidType.INSTANCE, parameters);
		return method;
	}

}
