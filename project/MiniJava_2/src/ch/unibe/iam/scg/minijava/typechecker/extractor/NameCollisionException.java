package ch.unibe.iam.scg.minijava.typechecker.extractor;

public class NameCollisionException extends Exception {

	private static final long serialVersionUID = 1L;

	public NameCollisionException(String name) {
		super(name);
	}

}
