package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.minijava.typechecker.extractor.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.TypesPreExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.GlobalScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;

/**
 * Change at will.
 * 
 * @author kursjan
 *
 */
public class TypeChecker {

	protected GlobalScope globalScope;

	public TypeChecker() {
		this.globalScope = new GlobalScope();
	}

	public boolean check(Object o) {
		INode n = (INode) o;
		try {
			// first pass: extract flat class declarations
			Map<String, IType> types = (new TypesPreExtractor()).extract(n,
					this.globalScope.getImplicitSuperType());
			for (Map.Entry<String, IType> entry : types.entrySet()) {
				if (this.globalScope.hasType(entry.getKey())) {
					throw new NameCollisionException(entry.getKey());
				}
				this.globalScope.putType(entry.getKey(), entry.getValue());
			}
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

}
