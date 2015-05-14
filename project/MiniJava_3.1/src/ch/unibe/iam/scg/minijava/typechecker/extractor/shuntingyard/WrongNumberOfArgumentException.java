package ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard;

import ch.unibe.iam.scg.minijava.typechecker.TypeCheckException;

public class WrongNumberOfArgumentException extends TypeCheckException {

	private static final long serialVersionUID = 1L;

	public WrongNumberOfArgumentException(int actual, int expected) {
		super("got " + actual + " parameters, expected " + expected);
	}

}
