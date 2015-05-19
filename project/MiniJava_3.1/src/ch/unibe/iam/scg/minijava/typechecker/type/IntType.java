package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class IntType extends AbstractPrimitiveType {

	public static final IntType INSTANCE = new IntType();

	public IntType() {
		super("int");
	}

	@Override
	public Type toBcelType() {
		return Type.INT;
	}

}
