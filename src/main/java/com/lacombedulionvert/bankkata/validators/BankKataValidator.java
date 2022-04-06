package com.lacombedulionvert.bankkata.validators;

import com.lacombedulionvert.bankkata.exceptions.InputValidationException;

public interface BankKataValidator<T> {

	/**
	 * Make some operation to the dto for their validation
	 * @param dto
	 * @return
	 * @throws InputValidationException
	 */
	boolean isValid(T dto) throws InputValidationException;
}
