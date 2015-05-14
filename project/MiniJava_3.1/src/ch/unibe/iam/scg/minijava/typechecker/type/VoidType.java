package ch.unibe.iam.scg.minijava.typechecker.type;

public class VoidType extends Type {

	public static final VoidType INSTANCE = new VoidType();

	public VoidType() {
		super("void", NullType.INSTANCE);
	}

}
