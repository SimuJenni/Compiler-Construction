package ch.unibe.iam.scg.minijava.typechecker.type;

public class ArrayType extends Type {

	protected IType elementType;

	public ArrayType(IType elementType) {
		super(elementType.getName() + "[]", NullType.INSTANCE);
		this.elementType = elementType;
	}

	public IType getElementType() {
		return this.elementType;
	}

}
