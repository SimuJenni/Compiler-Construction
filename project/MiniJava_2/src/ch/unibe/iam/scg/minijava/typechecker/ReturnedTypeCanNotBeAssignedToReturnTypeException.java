package ch.unibe.iam.scg.minijava.typechecker;

public class ReturnedTypeCanNotBeAssignedToReturnTypeException extends
		TypeCheckException {

	private static final long serialVersionUID = 1L;

	public ReturnedTypeCanNotBeAssignedToReturnTypeException(
			ClassEntry returnedType, ClassEntry returnType) {
		super("Returned " + returnedType.getName() + ", should return "
				+ returnType.getName());
	}

}
