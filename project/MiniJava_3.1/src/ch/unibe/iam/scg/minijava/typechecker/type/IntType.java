package ch.unibe.iam.scg.minijava.typechecker.type;

public class IntType extends Type {

	public static final IntType INSTANCE = new IntType();

	public IntType() {
		super("int", NullType.INSTANCE);
	}

}
