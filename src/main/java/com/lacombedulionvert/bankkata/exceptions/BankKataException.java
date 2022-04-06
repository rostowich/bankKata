package com.lacombedulionvert.bankkata.exceptions;

public class BankKataException extends Exception {

	public BankKataException(String errorMessage) {
		super(errorMessage);
	}
	public BankKataException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
