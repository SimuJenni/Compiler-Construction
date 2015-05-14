package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.TypeCheckException;

public class NameCollisionException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public NameCollisionException(String name) {
		super(name);
	}

}
