package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.minijava.typechecker.extractor.ScopeMapExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesPreExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.GlobalScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.scope.ScopeMap;
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
			GlobalScope globalScope = this.buildGlobalScope(n);
			ScopeMap scopeMap = this.buildScopeMap(n, globalScope);
			// second pass: extract full class declarations
			types = (new TypesExtractor()).extract(n, this.globalScope);
			return true;
		} catch (NameCollisionException exception) {
			exception.printStackTrace();
			return false;
		} catch (LookupException exception) {
			exception.printStackTrace();
			return false;
		}
	}

	protected ScopeMap buildScopeMap(INode n, GlobalScope globalScope) {
		return (new ScopeMapExtractor()).extract(n, globalScope);
	}

	protected GlobalScope buildGlobalScope(INode n)
			throws NameCollisionException, LookupException {
		GlobalScope globalScope = new GlobalScope();
		Map<String, IType> types = (new TypesPreExtractor()).extract(n,
				globalScope.getImplicitSuperType());
		for (Map.Entry<String, IType> entry : types.entrySet()) {
			if (globalScope.hasType(entry.getKey())) {
				throw new NameCollisionException(entry.getKey());
			}
			globalScope.putType(entry.getKey(), entry.getValue());
		}
		return globalScope;
	}

}
