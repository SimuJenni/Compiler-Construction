package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class ArrayType extends AbstractType {

	protected IType elementType;

	public ArrayType(IType elementType) {
		super(elementType.getName() + "[]", NullType.INSTANCE);
		this.elementType = elementType;
	}

	public IType getElementType() {
		return this.elementType;
	}

	@Override
	public Type toBcelType() {
		return new org.apache.bcel.generic.ArrayType(
				this.elementType.toBcelType(), 1);
	}

	@Override
	public boolean isPrimitive() {
		return false;
	}

	@Override
	public boolean isArray() {
		return true;
	}

	@Override
	public boolean isObject() {
		return false;
	}

}
