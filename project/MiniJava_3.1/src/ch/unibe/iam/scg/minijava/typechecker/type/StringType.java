package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class StringType extends AbstractType {

	public static final StringType INSTANCE = new StringType();

	public StringType() {
		super("String", NullType.INSTANCE);
	}

	@Override
	public Type toBcelType() {
		return Type.STRING;
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
		return true;
	}

}
