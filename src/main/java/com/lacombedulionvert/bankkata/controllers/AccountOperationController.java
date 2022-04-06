package com.lacombedulionvert.bankkata.controllers;

import java.math.BigDecimal;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacombedulionvert.bankkata.dto.AccountOperationDto;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.ErrorCode;
import com.lacombedulionvert.bankkata.exceptions.InputValidationException;
import com.lacombedulionvert.bankkata.exceptions.InsufficientFundException;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.ErrorMessage;
import com.lacombedulionvert.bankkata.services.AccountOperationService;
import com.lacombedulionvert.bankkata.validators.BankKataValidator;

public class AccountOperationController implements IAccountOperationController {

	private BankKataValidator<AccountOperationDto> accountOperationValidator;

	private ObjectMapper jsonMapper;

	private AccountOperationService accountOperationService;

	public AccountOperationController(BankKataValidator<AccountOperationDto> accountOperationValidator,
			ObjectMapper jsonMapper, AccountOperationService accountOperationService) {
		this.accountOperationValidator = accountOperationValidator;
		this.jsonMapper = jsonMapper;
		this.accountOperationService = accountOperationService;
	}

	@Override
	public String saveAmount(AccountOperationDto accountOperationDto) throws JsonProcessingException {

		try {
			accountOperationValidator.isValid(accountOperationDto);
		} catch (InputValidationException e) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.VALIDATION_ERROR_CODE, e.getLocalizedMessage()));
		}
		AccountOperation accountOperation;
		try {
			accountOperation = accountOperationService.saveAmount(Long.parseLong(accountOperationDto.getAccountId()),
					new BigDecimal(accountOperationDto.getAmount()));
		} catch (ElementNotFoundException ex) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.NOT_FOUND_ERROR_CODE, ex.getLocalizedMessage()));
		}

		return jsonMapper.writeValueAsString(accountOperation);
	}

	@Override
	public String retrieveAmount(AccountOperationDto accountOperationDto) throws JsonProcessingException {
		try {
			accountOperationValidator.isValid(accountOperationDto);
		} catch (InputValidationException e) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.VALIDATION_ERROR_CODE, e.getLocalizedMessage()));
		}
		AccountOperation accountOperation;
		try {
			accountOperation = accountOperationService.retrieveAmount(
					Long.parseLong(accountOperationDto.getAccountId()),
					new BigDecimal(accountOperationDto.getAmount()));
		} catch (ElementNotFoundException ex) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.NOT_FOUND_ERROR_CODE, ex.getLocalizedMessage()));
		} catch (InsufficientFundException in) {
			return jsonMapper.writeValueAsString(
					new ErrorMessage(ErrorCode.INSUFFICIENT_FUND_ERROR_CODE, in.getLocalizedMessage()));
		}

		return jsonMapper.writeValueAsString(accountOperation);
	}

	@Override
	public String checkOperations(Long accountId) throws JsonProcessingException {
		Collection<AccountOperation> operations;
		try {
			operations = accountOperationService.checkOperations(accountId);
		} catch (ElementNotFoundException e) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.NOT_FOUND_ERROR_CODE, e.getLocalizedMessage()));
		}
		return jsonMapper.writeValueAsString(operations);
	}

}
