package ch.unibe.iam.scg.minijava.typechecker.scope;

public class LookupException extends Exception {

	private static final long serialVersionUID = 1L;

	public LookupException(String name) {
		super(name);
	}

}
