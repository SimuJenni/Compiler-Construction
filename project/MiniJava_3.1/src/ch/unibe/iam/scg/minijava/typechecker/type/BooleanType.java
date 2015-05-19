package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class BooleanType extends AbstractPrimitiveType {

	public static final BooleanType INSTANCE = new BooleanType();

	public BooleanType() {
		super("boolean");
	}

	@Override
	public Type toBcelType() {
		return Type.BOOLEAN;
	}

}
