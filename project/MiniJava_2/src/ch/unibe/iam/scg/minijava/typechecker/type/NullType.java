package ch.unibe.iam.scg.minijava.typechecker.type;

public class NullType implements IType {

	public static final NullType INSTANCE = new NullType();

	@Override
	public String getName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IType getParent() {
		throw new UnsupportedOperationException();
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
