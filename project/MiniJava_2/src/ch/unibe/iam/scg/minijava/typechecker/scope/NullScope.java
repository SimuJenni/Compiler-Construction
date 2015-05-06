package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class NullScope implements IScope {

	public static final NullScope INSTANCE = new NullScope();

	@Override
	public IType lookupType(String name) throws LookupException {
		throw new LookupException(name);
	}

	@Override
	public Method lookupMethod(String name) throws LookupException {
		throw new LookupException(name);
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		throw new LookupException(name);
	}

}
