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
	public boolean canBeAssignedTo(IType type) {
		return false;
	}

}
