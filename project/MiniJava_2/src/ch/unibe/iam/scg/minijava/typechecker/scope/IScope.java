package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public interface IScope {

	public IScope getParent();

	public void setParent(IScope parent);

	public boolean hasType(String name);

	public IType getType(String name);

	public void putType(String name, IType type, IScope scope);

	public IType lookupType(String name) throws LookupException;

	public IScope lookupTypeScope(String name) throws LookupException;

	public boolean hasVariable(String name);

	public Variable getVariable(String name);

	public void putVariable(String name, Variable variable);

	public Variable lookupVariable(String name) throws LookupException;

	public boolean hasMethod(String name);

	public Method getMethod(String name);

	public void putMethod(String name, Method method);

	public Method lookupMethod(String name) throws LookupException;

}
