package ch.unibe.iam.scg.minijava.typechecker.type;

public class BooleanType extends Type {

	public static final BooleanType INSTANCE = new BooleanType();

	public BooleanType() {
		super("boolean", NullType.INSTANCE);
	}

}
