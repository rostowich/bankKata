package com.lacombedulionvert.bankkata.exceptions;

public class ElementNotFoundException extends BankKataException{

	public ElementNotFoundException(String errorMessage) {
		super(errorMessage);
	} 
	
	public ElementNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
