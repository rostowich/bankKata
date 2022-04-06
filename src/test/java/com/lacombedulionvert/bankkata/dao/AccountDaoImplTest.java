package com.lacombedulionvert.bankkata.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.lacombedulionvert.bankkata.objects.Account;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDaoImplTest {
	
	private AccountDao accountDao;

	private Account accountSaved;
	
	private Account accountToSave;
	
	@Before
	public void setUp() throws Exception {
		accountDao = new AccountDaoImpl();
		accountToSave = new Account("FR56908767656765", "Rostow GOKENG", new BigDecimal(500));
		
	}
	
	private Account _createSecondAccount() {
		return new Account("FR56908767656768", "Mikhael", new BigDecimal(90));
	}

	@Test
	public void test1Save() throws Exception {	
		accountSaved = accountDao.save(accountToSave);
    	Account secondAccountToSave = _createSecondAccount();
		Account secondAccountSaved = accountDao.save(secondAccountToSave);
		assertThat(secondAccountSaved.getId()).isEqualTo(2);
		assertThat(secondAccountSaved.getAccountNumber()).isEqualTo("FR56908767656768");
    	assertThat(secondAccountSaved.getAccountOwner()).isEqualTo("Mikhael");
    	assertThat(secondAccountSaved.getCurrentBalance().compareTo(new BigDecimal(90))).isEqualTo(0);
		
	}
	
	@Test
	public void test2FindById() throws Exception{
		Optional<Account> accountFound = accountDao.findById(1L);
		assertThat(accountFound.get()).isNotNull();
		assertThat(accountFound.get().getId()).isEqualTo(1);
		assertThat(accountFound.get().getAccountNumber()).isEqualTo("FR56908767656765");
    	assertThat(accountFound.get().getAccountOwner()).isEqualTo("Rostow GOKENG");
    	assertThat(accountFound.get().getCurrentBalance().compareTo(new BigDecimal(500))).isEqualTo(0);
		
	}
	
	@Test
	public void test3Update() throws Exception{
		Account accountToUpdate = new Account("FR96TEST", "Bernard", new BigDecimal(900));
		accountToUpdate.setId(1L);
		Account  accountUpdated = accountDao.update(1L, accountToUpdate);
		assertThat(accountUpdated.getId()).isEqualTo(1L);
		assertThat(accountUpdated.getAccountNumber()).isEqualTo("FR96TEST");
    	assertThat(accountUpdated.getAccountOwner()).isEqualTo("Bernard");
    	assertThat(accountUpdated.getCurrentBalance().compareTo(new BigDecimal(900))).isEqualTo(0);
		
	}
}
