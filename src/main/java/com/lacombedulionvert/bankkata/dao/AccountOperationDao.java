package com.lacombedulionvert.bankkata.dao;

import java.util.Collection;
import java.util.Optional;

import com.lacombedulionvert.bankkata.objects.AccountOperation;

public interface AccountOperationDao {

	/**
	 * Add a new account operation
	 * @param accountOperation
	 * @return
	 */
	AccountOperation save(AccountOperation accountOperation);
	
	/**
	 * List all the account operations
	 * @return
	 */
	Collection<AccountOperation> findAll();
	
	/**
	 * This method gets the last account operation. 
	 * In case there is any operation, it will return empty
	 * @return
	 */
	Optional<AccountOperation> getLastAccountOperation();
	
	/**
	 * List all the account operations for a specific account
	 * @return
	 */
	Collection<AccountOperation> findAll(Long acccountId);
}
