package com.lacombedulionvert.bankkata.services;

import java.util.Optional;

import com.lacombedulionvert.bankkata.dao.AccountDao;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.objects.Account;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Optional<Account> findAccountById(Long id) throws ElementNotFoundException {

		Optional<Account> accountToFind = accountDao.findById(id);
		if (accountToFind.isEmpty())
			throw new ElementNotFoundException("Account not found");

		return accountToFind;
	}

}
