package com.lacombedulionvert.bankkata.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.lacombedulionvert.bankkata.dao.AccountOperationDao;
import com.lacombedulionvert.bankkata.dao.AccountOperationDaoImpl;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.OperationType;

public class AccountOperationImplTest {

	private AccountOperationDao accountOperationDao;

	private AccountOperation accountOperationSaved;
	
	private Account account;

	@Before
	public void setUp() throws Exception {
		accountOperationDao = new AccountOperationDaoImpl();
		account = new Account("FR56", "Rostow" , new BigDecimal(0));
		account.setId(1L);
		AccountOperation accountOperation = new AccountOperation(OperationType.DEPOSIT, new BigDecimal(500));
		accountOperation.setAccount(account);
		accountOperationSaved = accountOperationDao.save(accountOperation);
	}

	private AccountOperation _createSecondAccountOperation() {
		AccountOperation accountOperation = new AccountOperation(OperationType.WITHDRAWAL, new BigDecimal(150));
		accountOperation.setAccount(account);
		return accountOperation;
	}

	@Test
	public void testSave() throws Exception {
		AccountOperation secondAccountOperationToSave = _createSecondAccountOperation();
		AccountOperation secondAccountOperationSaved = accountOperationDao.save(secondAccountOperationToSave);
		assertThat(secondAccountOperationSaved.getId()).isEqualTo(2);
		assertThat(secondAccountOperationSaved.getAmount().compareTo(new BigDecimal(150))).isEqualTo(0);
		assertThat(secondAccountOperationSaved.getOperationType()).isEqualTo(OperationType.WITHDRAWAL);

	}

	@Test
	public void testFindAll() throws Exception {
		Collection<AccountOperation> operations = accountOperationDao.findAll();
		assertThat(operations.size()).isEqualTo(1);

	}
	
	@Test
	public void testgetLastAccountOperation() throws Exception {
		Optional<AccountOperation> lastOperation = accountOperationDao.getLastAccountOperation();
		assertThat(lastOperation.get()).isNotNull();
		assertThat(lastOperation.get().getId()).isEqualTo(1);
		assertThat(lastOperation.get().getOperationType()).isEqualTo(OperationType.DEPOSIT);
		assertThat(lastOperation.get().getAmount().compareTo(new BigDecimal(500))).isEqualTo(0);

	}
	
	@Test
	public void testFindAllByAccountId() throws Exception {
		AccountOperation secondAccountOperationToSave = _createSecondAccountOperation();
		AccountOperation secondAccountOperationSaved = accountOperationDao.save(secondAccountOperationToSave);
		Collection<AccountOperation> operations = accountOperationDao.findAll(1L);
		assertThat(operations.size()).isEqualTo(2);
	}

}
