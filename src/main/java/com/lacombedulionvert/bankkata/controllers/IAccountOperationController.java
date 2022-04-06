package com.lacombedulionvert.bankkata.controllers;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lacombedulionvert.bankkata.dto.AccountOperationDto;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.InsufficientFundException;
import com.lacombedulionvert.bankkata.objects.AccountOperation;

public interface IAccountOperationController {

	/**
	 * Adds an amount to an existing account
	 * @param accountOperationDto
	 * @return
	 * @throws JsonProcessingException 
	 * @throws ElementNotFoundException
	 */
	String saveAmount(AccountOperationDto accountOperationDto) throws JsonProcessingException;
	
	/**
	 * Retrieve amount from an existing account
	 * @param accountOperationDto
	 * @return
	 * @throws JsonProcessingException 
	 * @throws ElementNotFoundException
	 * @throws InsufficientFundException
	 */
	String retrieveAmount(AccountOperationDto accountOperationDto) throws JsonProcessingException;
	
	/**
	 * Gets all the account operation for a specific account
	 * @param accountId
	 * @return
	 * @throws JsonProcessingException 
	 * @throws ElementNotFoundException
	 */
	String checkOperations(Long accountId) throws JsonProcessingException;
}
