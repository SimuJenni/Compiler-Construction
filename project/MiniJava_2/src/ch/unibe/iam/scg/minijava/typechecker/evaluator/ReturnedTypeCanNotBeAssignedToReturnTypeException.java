package ch.unibe.iam.scg.minijava.typechecker.evaluator;

import ch.unibe.iam.scg.minijava.typechecker.TypeCheckException;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class ReturnedTypeCanNotBeAssignedToReturnTypeException extends
		TypeCheckException {

	private static final long serialVersionUID = 1L;

	public ReturnedTypeCanNotBeAssignedToReturnTypeException(
			IType returnedType, IType returnType) {
		super("Returned " + returnedType.getName() + ", should return "
				+ returnType.getName());
	}

}
