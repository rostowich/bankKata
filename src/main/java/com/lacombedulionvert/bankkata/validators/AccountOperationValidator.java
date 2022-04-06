package com.lacombedulionvert.bankkata.validators;

import java.math.BigDecimal;

import com.lacombedulionvert.bankkata.dto.AccountOperationDto;
import com.lacombedulionvert.bankkata.exceptions.InputValidationException;

public class AccountOperationValidator implements BankKataValidator<AccountOperationDto> {

	@Override
	public boolean isValid(AccountOperationDto dto) throws InputValidationException {
		if (dto == null || dto.getAccountId() == null || dto.getAmount() == null || dto.getAccountId().isBlank()
				|| dto.getAmount().isBlank())
			throw new InputValidationException("Input parameters cannot be empty");

		try {
			Long.parseLong(dto.getAccountId());
			new BigDecimal(dto.getAmount());
		} catch (Exception e) {
			throw new InputValidationException("Error with input paramaters");
		}
		return true;
	}

}
