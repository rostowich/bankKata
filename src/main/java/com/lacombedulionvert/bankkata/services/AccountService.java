package com.lacombedulionvert.bankkata.services;

import java.util.Optional;

import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.objects.Account;

public interface AccountService {

	/**
	 * This method is used to know whether the account exists or not
	 * @param id
	 * @return
	 * @throws ElementNotFoundException
	 */
	Optional<Account> findAccountById(Long id) throws ElementNotFoundException;
}
