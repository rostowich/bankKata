package com.lacombedulionvert.bankkata.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.OperationType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountOperationDaoImplTest {

	private  AccountOperationDao accountOperationDao;
	
	private  Account account;
	
	private AccountOperation accountOperation;

	@Before
	public  void setUp() throws Exception {
		accountOperationDao = new AccountOperationDaoImpl();
		account = new Account("FR56", "Rostow" , new BigDecimal(0));
		account.setId(1L);
		accountOperation = new AccountOperation(OperationType.DEPOSIT, new BigDecimal(500));
		accountOperation.setAccount(account);
		
	}

	private AccountOperation _createSecondAccountOperation() {
		AccountOperation accountOperation = new AccountOperation(OperationType.WITHDRAWAL, new BigDecimal(150));
		accountOperation.setAccount(account);
		return accountOperation;
	}

	@Test
	public void test1Save() throws Exception {
		AccountOperation accountOperationSaved = accountOperationDao.save(accountOperation);
		assertThat(accountOperationSaved.getId()).isEqualTo(1);
		assertThat(accountOperationSaved.getAmount().compareTo(new BigDecimal(500))).isEqualTo(0);
		assertThat(accountOperationSaved.getOperationType()).isEqualTo(OperationType.DEPOSIT);
		
		AccountOperation secondAccountOperationToSave = _createSecondAccountOperation();
		AccountOperation secondAccountOperationSaved = accountOperationDao.save(secondAccountOperationToSave);
		assertThat(secondAccountOperationSaved.getId()).isEqualTo(2);
		assertThat(secondAccountOperationSaved.getAmount().compareTo(new BigDecimal(150))).isEqualTo(0);
		assertThat(secondAccountOperationSaved.getOperationType()).isEqualTo(OperationType.WITHDRAWAL);
		
	}

	@Test
	public void test2FindAll() throws Exception {
		Collection<AccountOperation> operations = accountOperationDao.findAll();
		assertThat(operations.size()).isEqualTo(2);

	}
	
	@Test
	public void test3GetLastAccountOperation() throws Exception {
		Optional<AccountOperation> lastOperation = accountOperationDao.getLastAccountOperation();
		assertThat(lastOperation.get()).isNotNull();
		assertThat(lastOperation.get().getId()).isEqualTo(2);
		assertThat(lastOperation.get().getOperationType()).isEqualTo(OperationType.WITHDRAWAL);
		assertThat(lastOperation.get().getAmount().compareTo(new BigDecimal(150))).isEqualTo(0);

	}
	
	@Test
	public void test4FindAllByAccountId() throws Exception {
		Collection<AccountOperation> operations = accountOperationDao.findAll(1L);
		assertThat(operations.size()).isEqualTo(2);
	}

}
