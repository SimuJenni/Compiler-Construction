package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public class VoidType extends AbstractPrimitiveType {

	public static final VoidType INSTANCE = new VoidType();

	public VoidType() {
		super("void");
	}

	@Override
	public Type toBcelType() {
		return Type.VOID;
	}

}
