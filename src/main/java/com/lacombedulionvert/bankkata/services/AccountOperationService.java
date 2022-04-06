package com.lacombedulionvert.bankkata.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.InsufficientFundException;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;

public interface AccountOperationService {

	/**
	 * This method adds an amount in an account.
	 * 1) It looks for the account whether it exists 
	 * 2) Looks for the last operation to have the account balance 
	 * 3) Creates the operation account with the type = DEPOSIT 
	 * 4) Saves the operation account
	 * 
	 * @param accountId
	 * @param amount
	 * @return
	 * @throws ElementNotFoundException
	 */
	AccountOperation saveAmount(Long accountId, BigDecimal amount) throws ElementNotFoundException;

	/**
	 * This method retrieves some amount from the account 
	 * 1) It looks for the account whether it exists 
	 * 2) Looks for the last operation to have the account balance 
	 * 3) Check if the amount is great than the current account balance.
	 * Otherwise it will throw the InsufficientFundException
	 * 4) Creates the operation account with the type = WITHDRAWAL 
	 * 5) Saves the operation account
	 * 
	 * @param accountId
	 * @param amount
	 * @return
	 * @throws ElementNotFoundException
	 * @throws InsufficientFundException
	 */
	AccountOperation retrieveAmount(Long accountId, BigDecimal amount)
			throws ElementNotFoundException, InsufficientFundException;

	/**
	 * This methods list all the account operations from an account
	 * 
	 * @return
	 */
	Collection<AccountOperation> checkOperations(Long accountId) throws ElementNotFoundException;
}
