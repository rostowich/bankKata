package com.lacombedulionvert.bankkata.exceptions;

public class InsufficientFundException extends BankKataException{

	public InsufficientFundException(String errorMessage) {
		super(errorMessage);
	} 
	
	public InsufficientFundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
