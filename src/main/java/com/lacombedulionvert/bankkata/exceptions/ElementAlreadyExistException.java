package com.lacombedulionvert.bankkata.exceptions;

public class ElementAlreadyExistException extends BankKataException{

	public ElementAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
	public ElementAlreadyExistException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
