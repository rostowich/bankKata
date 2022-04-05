package com.lacombedulionvert.bankkata.dao;

import java.util.Optional;

import com.lacombedulionvert.bankkata.objects.Account;

public interface AccountDao {

	/**
	 * Used to save a new account
	 * @param account
	 * @return
	 */
	Account save(Account account);
	
	/**
	 * Used to update an account
	 * @param accountNumber
	 * @param account
	 * @return
	 */
	Account update(Long id, Account account);
	
	/**
	 * Find an account by its id
	 * @param id
	 * @return
	 */
	Optional<Account> findById(Long id);
}
