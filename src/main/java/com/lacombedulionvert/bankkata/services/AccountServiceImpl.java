package com.lacombedulionvert.bankkata.services;

import java.math.BigDecimal;
import java.util.Optional;

import com.lacombedulionvert.bankkata.dao.AccountDao;
import com.lacombedulionvert.bankkata.dao.AccountOperationDao;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	private AccountOperationDao accountOperationDao;

	public AccountServiceImpl(AccountDao accountDao, AccountOperationDao accountOperationDao) {
		super();
		this.accountDao = accountDao;
		this.accountOperationDao = accountOperationDao;
	}



	public Optional<Account> findAccountById(Long id) throws ElementNotFoundException {

		Optional<Account> accountToFind = accountDao.findById(id);
		if (accountToFind.isEmpty())
			throw new ElementNotFoundException("Account not found");

		//calculate the account balance
		Optional<AccountOperation> lastOperation = accountOperationDao.getLastAccountOperation();
		BigDecimal accountBalance = lastOperation.isEmpty() ? new BigDecimal(0) 
				: lastOperation.get().getAccountCurrentBalance();
		accountToFind.get().setCurrentBalance(accountBalance);
		return accountToFind;
	}

}
