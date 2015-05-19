package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class CustomType extends AbstractType {

	public CustomType(String name, IType parent) {
		super(name, parent);
	}

	@Override
	public Type toBcelType() {
		return new org.apache.bcel.generic.ObjectType(this.getName());
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
