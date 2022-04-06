package com.lacombedulionvert.bankkata.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import com.lacombedulionvert.bankkata.dao.AccountOperationDao;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.InsufficientFundException;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.OperationType;

public class AccountOperationServiceImpl implements AccountOperationService {

	private AccountService accountService;

	private AccountOperationDao accountOperationDao;

	private DateUtilService dateUtilService;

	public AccountOperationServiceImpl(AccountService accountService, AccountOperationDao accountOperationDao,
			DateUtilService dateUtilService) {
		super();
		this.accountService = accountService;
		this.accountOperationDao = accountOperationDao;
		this.dateUtilService = dateUtilService;
	}

	public AccountOperation saveAmount(Long accountId, BigDecimal amount) throws ElementNotFoundException {

		Optional<Account> accountFound = accountService.findAccountById(accountId);
		Optional<AccountOperation> lastOperation = accountOperationDao.getLastAccountOperation();
		AccountOperation accountOperation = new AccountOperation(OperationType.DEPOSIT, amount);
		accountOperation.setAccount(accountFound.get());
		BigDecimal accountBalance = lastOperation.isEmpty() ? new BigDecimal(0)
				: lastOperation.get().getAccountCurrentBalance();
		accountOperation.setAccountBalance(accountBalance);
		accountOperation.setOperationDate(dateUtilService.getCurrentDateTime());

		return accountOperationDao.save(accountOperation);
	}

	public AccountOperation retrieveAmount(Long accountId, BigDecimal amount)
			throws ElementNotFoundException, InsufficientFundException {

		Optional<Account> accountFound = accountService.findAccountById(accountId);
		Optional<AccountOperation> lastOperation = accountOperationDao.getLastAccountOperation();
		BigDecimal accountBalance = lastOperation.isEmpty() ? new BigDecimal(0)
				: lastOperation.get().getAccountCurrentBalance();
		if(amount.compareTo(accountBalance) > 0)
			throw new InsufficientFundException("Insuficient funds in the account");
		
		AccountOperation accountOperation = new AccountOperation(OperationType.WITHDRAWAL, amount);
		accountOperation.setAccount(accountFound.get());
		accountOperation.setAccountBalance(accountBalance);
		accountOperation.setOperationDate(dateUtilService.getCurrentDateTime());
		
		return  accountOperationDao.save(accountOperation);
	}

	public Collection<AccountOperation> checkOperations(Long accountId) throws ElementNotFoundException {
		accountService.findAccountById(accountId);
		return accountOperationDao.findAll(accountId);
	}

}
