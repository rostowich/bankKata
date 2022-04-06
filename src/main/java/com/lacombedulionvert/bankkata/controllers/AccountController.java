package com.lacombedulionvert.bankkata.controllers;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.ErrorCode;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.ErrorMessage;
import com.lacombedulionvert.bankkata.services.AccountService;

public class AccountController implements IAccountController{

	private ObjectMapper jsonMapper;

	private AccountService accountService;
	
	public AccountController(ObjectMapper jsonMapper, AccountService accountService) {
		super();
		this.jsonMapper = jsonMapper;
		this.accountService = accountService;
	}

	@Override
	public String accountStatement(Long accountId) throws JsonProcessingException {
		Optional<Account> accountStatement;
		try {
			accountStatement = accountService.findAccountById(accountId);
		} catch (ElementNotFoundException e) {
			return jsonMapper
					.writeValueAsString(new ErrorMessage(ErrorCode.NOT_FOUND_ERROR_CODE, e.getLocalizedMessage()));
		}
		
		return jsonMapper.writeValueAsString(accountStatement.get());
	}

}
