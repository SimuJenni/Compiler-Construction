package ch.unibe.iam.scg.minijava.typechecker.type;

public class ArrayType extends AbstractType {

	public ArrayType(IType elementType) {
		super(elementType.getName() + "[]", NullType.INSTANCE);
	}

}
