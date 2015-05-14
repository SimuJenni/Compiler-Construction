package ch.unibe.iam.scg.minijava.typechecker.evaluator;

import ch.unibe.iam.scg.minijava.typechecker.TypeCheckException;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;

public class IncompatibleTypesException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public IncompatibleTypesException(IType returnedType, IType returnType) {
		super("Returned " + returnedType.getName() + ", should return "
				+ returnType.getName());
	}

}
