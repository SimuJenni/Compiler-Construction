package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class ObjectType extends AbstractType {

	public static final ObjectType INSTANCE = new ObjectType();

	public ObjectType() {
		super("Object", NullType.INSTANCE);
	}

	@Override
	public Type toBcelType() {
		return Type.OBJECT;
	}
	
	@Override
	public boolean isVoid() {
		return false;
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
