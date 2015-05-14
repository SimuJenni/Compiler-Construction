package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class NullScope implements IScope {

	public static final NullScope INSTANCE = new NullScope();

	@Override
	public IScope getParent() {
		return this;
	}

	@Override
	public void setParent(IScope parent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasType(String name) {
		return false;
	}

	@Override
	public IType getType(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putType(String name, IType type, IScope scope) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IScope lookupTypeScope(String name) throws LookupException {
		throw new LookupException(name);
	}

	@Override
	public IType lookupType(String name) throws LookupException {
		throw new LookupException(name);
	}

	@Override
	public boolean hasVariable(String name) {
		return false;
	}

	@Override
	public Variable getVariable(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putVariable(String name, Variable variable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable lookupVariable(String name) throws LookupException {
		throw new LookupException(name);
	}

	@Override
	public boolean hasMethod(String name) {
		return false;
	}

	@Override
	public Method getMethod(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putMethod(String name, Method method) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Method lookupMethod(String name) throws LookupException {
		throw new LookupException(name);
	}

}
