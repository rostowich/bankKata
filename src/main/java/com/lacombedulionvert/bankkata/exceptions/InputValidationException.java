package com.lacombedulionvert.bankkata.exceptions;

public class InputValidationException extends BankKataException {

	public InputValidationException(String errorMessage) {
		super(errorMessage);
	}

	public InputValidationException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
