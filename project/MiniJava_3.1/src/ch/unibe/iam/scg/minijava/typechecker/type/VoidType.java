package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class VoidType extends AbstractType {

	public static final VoidType INSTANCE = new VoidType();

	public VoidType() {
		super("void", NullType.INSTANCE);
	}

	@Override
	public Type toBcelType() {
		return Type.VOID;
	}
	
	@Override
	public boolean isVoid() {
		return true;
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
