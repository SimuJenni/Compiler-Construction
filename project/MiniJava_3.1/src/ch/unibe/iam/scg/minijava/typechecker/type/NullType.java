package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;


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
	public boolean canBeAssignedTo(IType type) {
		return false;
	}

	@Override
	public Type toBcelType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPrimitive() {
		return false;
	}

	@Override
	public boolean isArray() {
		return false;
	}

	@Override
	public boolean isObject() {
		return false;
	}

}
