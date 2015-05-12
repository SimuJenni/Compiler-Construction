package ch.unibe.iam.scg.minijava.typechecker;

public class SymbolNotFoundException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public SymbolNotFoundException(String key) {
		super(key);
	}

}
