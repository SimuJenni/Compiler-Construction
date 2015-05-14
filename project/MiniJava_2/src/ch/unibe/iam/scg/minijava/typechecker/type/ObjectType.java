package ch.unibe.iam.scg.minijava.typechecker.type;

public class ObjectType extends Type {

	public static final ObjectType INSTANCE = new ObjectType();

	public ObjectType() {
		super("Object", NullType.INSTANCE);
	}

}
