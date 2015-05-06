package ch.unibe.iam.scg.minijava.typechecker.type;

public interface IType {

	public String getName();

	public IType getParent();

	public boolean hasVariable(String name);

	public Variable getVariable(String name);

	public void putVariable(String name, Variable variable);

	public Variable lookupVariable(String name) throws LookupException;

	public boolean hasMethod(String name);

	public Method getMethod(String name);

	public void putMethod(String name, Method method);

	public Method lookupMethod(String name) throws LookupException;

}