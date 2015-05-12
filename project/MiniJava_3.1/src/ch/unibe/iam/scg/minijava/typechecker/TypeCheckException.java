package ch.unibe.iam.scg.minijava.typechecker;

public class TypeCheckException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TypeCheckException() {
		super();
	}

	public TypeCheckException(String message) {
		super(message);
	}

	public TypeCheckException(Throwable cause) {
		super(cause);
	}

	public TypeCheckException(String message, Throwable cause) {
		super(message, cause);
	}

}
