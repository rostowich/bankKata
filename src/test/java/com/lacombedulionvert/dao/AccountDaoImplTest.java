package com.lacombedulionvert.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.lacombedulionvert.bankkata.dao.AccountDao;
import com.lacombedulionvert.bankkata.dao.AccountDaoImpl;
import com.lacombedulionvert.bankkata.objects.Account;

public class AccountDaoImplTest {
	
	private AccountDao accountDao;

	private Account accountSaved;
	
	@Before
	public void setUp() throws Exception {
		accountDao = new AccountDaoImpl();
		Account accountToSave = new Account("FR56908767656765", "Rostow GOKENG", new BigDecimal(500));
		accountSaved = accountDao.save(accountToSave);
	}
	
	private Account _createSecondAccount() {
		return new Account("FR56908767656768", "Mikhael", new BigDecimal(90));
	}

	@Test
	public void testSave() throws Exception {		
    	Account secondAccountToSave = _createSecondAccount();
		Account secondAccountSaved = accountDao.save(secondAccountToSave);
		assertThat(secondAccountSaved.getId()).isEqualTo(2);
		assertThat(secondAccountSaved.getAccountNumber()).isEqualTo("FR56908767656768");
    	assertThat(secondAccountSaved.getAccountOwner()).isEqualTo("Mikhael");
    	assertThat(secondAccountSaved.getCurrentBalance().compareTo(new BigDecimal(90))).isEqualTo(0);
		
	}
	
	@Test
	public void testFindById() throws Exception{
		Optional<Account> accountFound = accountDao.findById(1L);
		assertThat(accountFound.get()).isNotNull();
		assertThat(accountFound.get().getId()).isEqualTo(1);
		assertThat(accountFound.get().getAccountNumber()).isEqualTo("FR56908767656765");
    	assertThat(accountFound.get().getAccountOwner()).isEqualTo("Rostow GOKENG");
    	assertThat(accountFound.get().getCurrentBalance().compareTo(new BigDecimal(500))).isEqualTo(0);
		
	}
	
	@Test
	public void testUpdate() throws Exception{
		Account accountToUpdate = new Account("FR96TEST", "Bernard", new BigDecimal(900));
		accountToUpdate.setId(1L);
		Account  accountUpdated = accountDao.update(1L, accountToUpdate);
		assertThat(accountUpdated.getId()).isEqualTo(1L);
		assertThat(accountUpdated.getAccountNumber()).isEqualTo("FR96TEST");
    	assertThat(accountUpdated.getAccountOwner()).isEqualTo("Bernard");
    	assertThat(accountUpdated.getCurrentBalance().compareTo(new BigDecimal(900))).isEqualTo(0);
		
	}
}
