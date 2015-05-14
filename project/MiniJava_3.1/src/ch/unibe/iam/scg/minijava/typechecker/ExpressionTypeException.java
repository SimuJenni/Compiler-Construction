package ch.unibe.iam.scg.minijava.typechecker;

public class ExpressionTypeException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public ExpressionTypeException(Types type) {
		super(type.getName() + " required");
	}

}
