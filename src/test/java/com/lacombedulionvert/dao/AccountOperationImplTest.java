package com.lacombedulionvert.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.lacombedulionvert.bankkata.dao.AccountOperationDao;
import com.lacombedulionvert.bankkata.dao.AccountOperationDaoImpl;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.OperationType;

public class AccountOperationImplTest {

	private AccountOperationDao accountOperationDao;

	private AccountOperation accountOperationSaved;

	@Before
	public void setUp() throws Exception {
		accountOperationDao = new AccountOperationDaoImpl();
		AccountOperation accountOperation = new AccountOperation(OperationType.DEPOSIT, new BigDecimal(500));
		accountOperationSaved = accountOperationDao.save(accountOperation);
	}

	private AccountOperation _createSecondAccountOperation() {
		return new AccountOperation(OperationType.WITHDRAWAL, new BigDecimal(150));
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

}
