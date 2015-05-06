package ch.unibe.iam.scg.minijava.typechecker.type;

public class ArrayType extends Type {

	public ArrayType(IType elementType) {
		super(elementType.getName() + "[]", NullType.INSTANCE);
	}

}
