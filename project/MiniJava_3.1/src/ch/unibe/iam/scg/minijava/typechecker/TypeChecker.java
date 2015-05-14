package ch.unibe.iam.scg.minijava.typechecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.minijava.typechecker.evaluator.Evaluator;
import ch.unibe.iam.scg.minijava.typechecker.extractor.ScopeMapExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NullScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.Scope;
import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.ObjectType;
import ch.unibe.iam.scg.minijava.typechecker.type.StringType;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

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
			IScope globalScope = this.buildGlobalScope(n);
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

	protected IScope buildGlobalScope(INode n) {
		IScope globalScope = new Scope(NullScope.INSTANCE);
		List<IType> builtinTypes = new ArrayList<IType>();
		builtinTypes.add(ObjectType.INSTANCE);
		builtinTypes.add(IntType.INSTANCE);
		builtinTypes.add(BooleanType.INSTANCE);
		builtinTypes.add(VoidType.INSTANCE);
		builtinTypes.add(StringType.INSTANCE);
		builtinTypes.add(new ArrayType(IntType.INSTANCE));
		builtinTypes.add(new ArrayType(StringType.INSTANCE));
		for (IType type : builtinTypes) {
			IScope typeScope = new Scope(globalScope);
			globalScope.putType(type.getName(), type, typeScope);
		}
		List<IType> types = (new TypesExtractor()).extract(n,
				ObjectType.INSTANCE);
		for (IType type : types) {
			if (globalScope.hasType(type.getName())) {
				throw new NameCollisionException(type.getName());
			}
			IScope parentTypeScope = globalScope.lookupTypeScope(type
					.getParent().getName());
			IScope typeScope = new Scope(parentTypeScope);
			globalScope.putType(type.getName(), type, typeScope);
		}
		return globalScope;
	}

	protected Map<INode, IScope> buildScopeMap(INode n, IScope scope) {
		return (new ScopeMapExtractor()).extract(n, scope);
	}

	protected void evaluate(INode n, Map<INode, IScope> scopeMap)
			throws LookupException {
		Evaluator evaluator = new Evaluator(scopeMap);
		evaluator.evaluate(n);
	}

}
