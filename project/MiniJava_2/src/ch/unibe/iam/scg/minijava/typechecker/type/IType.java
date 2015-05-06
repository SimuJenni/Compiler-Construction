package ch.unibe.iam.scg.minijava.typechecker.type;

public interface IType {

	public abstract String getName();

	public abstract IType getSuperType();

	public abstract boolean hasVariable(String name);

	public abstract Variable getVariable(String name);

	public abstract void putVariable(String name, Variable variable);

	public abstract boolean hasMethod(String name);

	public abstract Method getMethod(String name);

	public abstract void putMethod(String name, Method method);

}