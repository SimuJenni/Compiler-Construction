package ch.unibe.iam.scg.minijava.typechecker;

public class SymbolRedeclaredException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public SymbolRedeclaredException(String key) {
		super(key);
	}

}
