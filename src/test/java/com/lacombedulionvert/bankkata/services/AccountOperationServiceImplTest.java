package com.lacombedulionvert.bankkata.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lacombedulionvert.bankkata.dao.AccountOperationDao;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.exceptions.InsufficientFundException;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.objects.AccountOperation;
import com.lacombedulionvert.bankkata.objects.OperationType;

public class AccountOperationServiceImplTest {

	@InjectMocks
	AccountOperationServiceImpl accountOperationServiceImpl;

	@Mock
	AccountService accountServiceMock;

	@Mock
	AccountOperationDao accountOperationDaoMock;

	@Mock
	DateUtilService dateUtilServiceMock;

	private Account sampleAccount;

	private AccountOperation accountOperation;

	/**
	 * Function used to init the mocked objects before performing the tests
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sampleAccount = new Account("FR6778998", "Rostow", new BigDecimal(0));
		sampleAccount.setId(1L);

		accountOperation = new AccountOperation(OperationType.DEPOSIT, new BigDecimal(100));
		accountOperation.setAccount(sampleAccount);
		accountOperation.setAccountBalance(new BigDecimal(0));
		accountOperation.setId(1L);
		accountOperation.setOperationDate(LocalDateTime.of(2022, Month.APRIL, 06, 10, 30, 00));
	}

	@Test
	public void testSaveAmountWithNonExistingAccount() throws Exception {
		// Mocking the accountService method
		when(accountServiceMock.findAccountById(1L)).thenThrow(new ElementNotFoundException("Account not found"));
		try {
			accountOperationServiceImpl.saveAmount(1L, new BigDecimal(100));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ElementNotFoundException.class).hasMessage("Account not found");
		}
	}

	@Test
	public void testSaveAmount() throws Exception {
		// Mocking the dependencies
		when(accountServiceMock.findAccountById(1L)).thenReturn(Optional.of(sampleAccount));
		when(accountOperationDaoMock.getLastAccountOperation()).thenReturn(Optional.empty());
		when(dateUtilServiceMock.getCurrentDateTime()).thenReturn(LocalDateTime.of(2022, Month.APRIL, 06, 10, 30, 00));
		when(accountOperationDaoMock.save(any(AccountOperation.class))).thenReturn(accountOperation);

		AccountOperation accountOperationSaved = accountOperationServiceImpl.saveAmount(1L, new BigDecimal(100));
		verify(accountServiceMock, times(1)).findAccountById(1L);
		verify(accountOperationDaoMock, times(1)).getLastAccountOperation();
		verify(dateUtilServiceMock, times(1)).getCurrentDateTime();

		assertThat(accountOperationSaved.getAccountBalance().compareTo(new BigDecimal(0))).isEqualTo(0);
		assertThat(accountOperationSaved.getAmount().compareTo(new BigDecimal(100))).isEqualTo(0);
		assertThat(accountOperationSaved.getOperationType()).isEqualTo(OperationType.DEPOSIT);
		assertThat(accountOperationSaved.getOperationDate())
				.isEqualTo(LocalDateTime.of(2022, Month.APRIL, 06, 10, 30, 00));
	}

	@Test
	public void testRetrieveAmountWithNonExistingAccount() throws Exception {
		// Mocking the accountService method
		when(accountServiceMock.findAccountById(1L)).thenThrow(new ElementNotFoundException("Account not found"));
		try {
			accountOperationServiceImpl.retrieveAmount(1L, new BigDecimal(100));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(ElementNotFoundException.class)
			.hasMessage("Account not found");
		}
	}

	@Test
	public void testRetrieveAmountWithInsufficientFundException() throws Exception {
		// Mocking the dependencies
		when(accountServiceMock.findAccountById(1L)).thenReturn(Optional.of(sampleAccount));
		when(accountOperationDaoMock.getLastAccountOperation()).thenReturn(Optional.empty());

		try {
			accountOperationServiceImpl.retrieveAmount(1L, new BigDecimal(100));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(InsufficientFundException.class)
			.hasMessage("Insuficient funds in the account");
		}
	}

	@Test
	public void testcheckOperation() throws Exception {
		// Mocking the dependencies
		when(accountServiceMock.findAccountById(1L)).thenReturn(Optional.of(sampleAccount));
		Collection<AccountOperation> operations = new ArrayList<AccountOperation>();
		operations.add(accountOperation);
		when(accountOperationDaoMock.findAll(1L)).thenReturn(operations);

		Collection<AccountOperation> operationFound = accountOperationServiceImpl.checkOperations(1L);
		verify(accountServiceMock, times(1)).findAccountById(1L);
		verify(accountOperationDaoMock, times(1)).findAll(1L);
		
		assertThat(operationFound.size()).isEqualTo(1);
	}

}
