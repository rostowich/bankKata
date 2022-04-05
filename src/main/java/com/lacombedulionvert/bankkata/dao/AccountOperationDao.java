package com.lacombedulionvert.bankkata.dao;

import java.util.Collection;

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
	Collection<AccountOperation> findAll ();
}
