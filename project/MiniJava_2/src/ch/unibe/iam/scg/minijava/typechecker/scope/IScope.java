package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public interface IScope {

	public IType lookupType(String name) throws LookupException;

	public Method lookupMethod(String name) throws LookupException;

	public Variable lookupVariable(String name) throws LookupException;

}
