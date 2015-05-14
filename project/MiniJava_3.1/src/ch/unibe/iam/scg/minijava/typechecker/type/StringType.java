package ch.unibe.iam.scg.minijava.typechecker.type;

public class StringType extends Type {

	public static final StringType INSTANCE = new StringType();

	public StringType() {
		super("String", NullType.INSTANCE);
	}

}
