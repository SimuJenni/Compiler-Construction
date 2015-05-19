package ch.unibe.iam.scg.minijava.typechecker.type;

public abstract class AbstractPrimitiveType extends AbstractType {

	public AbstractPrimitiveType(String name) {
		super(name, NullType.INSTANCE);
	}

	@Override
	public boolean isPrimitive() {
		return true;
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
