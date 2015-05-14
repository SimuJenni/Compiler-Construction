package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.TypeCheckException;

public class LookupException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public LookupException(String name) {
		super(name);
	}

}
