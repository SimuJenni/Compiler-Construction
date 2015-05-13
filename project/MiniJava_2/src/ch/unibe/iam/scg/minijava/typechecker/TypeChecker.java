package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Collection;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.Evaluator;
import ch.unibe.iam.scg.minijava.typechecker.extractor.ScopeMapExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.GlobalScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {

	public boolean check(Object o) {
		INode n = (INode) o;
		try {
			// first pass: build global scope
			GlobalScope globalScope = this.buildGlobalScope(n);
			// second pass: build all scopes
			Map<INode, IScope> scopeMap = this.buildScopeMap(n, globalScope);
			// third pass: evaluate
			this.evaluate(n, scopeMap);
			return true;
		} catch (TypeCheckException exception) {
			exception.printStackTrace();
			return false;
		}
	}

	protected GlobalScope buildGlobalScope(INode n) {
		GlobalScope globalScope = new GlobalScope();
		Collection<IType> types = (new TypesExtractor()).extract(n,
				globalScope.getImplicitSuperType());
		for (IType type : types) {
			if (globalScope.hasType(type.getName())) {
				throw new NameCollisionException(type.getName());
			}
			globalScope.putType(type.getName(), type);
		}
		return globalScope;
	}

	protected Map<INode, IScope> buildScopeMap(INode n, GlobalScope globalScope) {
		return (new ScopeMapExtractor()).extract(n, globalScope);
	}

	protected void evaluate(INode n, Map<INode, IScope> scopeMap)
			throws LookupException {
		Evaluator evaluator = new Evaluator(scopeMap);
		evaluator.evaluate(n);
	}

}
