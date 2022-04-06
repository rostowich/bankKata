package com.lacombedulionvert.bankkata.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAccountController {
	
	/**
	 * display the statement of a specific account
	 * @param accountId
	 * @return
	 * @throws JsonProcessingException
	 */
	String accountStatement(Long accountId) throws JsonProcessingException;
}
