package com.lacombedulionvert.bankkata.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lacombedulionvert.bankkata.dao.AccountDao;
import com.lacombedulionvert.bankkata.exceptions.ElementNotFoundException;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.services.AccountServiceImpl;

public class AccountServiceImplTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;  
	
    @Mock
    AccountDao accountDaoMock;
    
    private Account sampleAccount;	
    /**
	 * Function used to init the mocked objects before performing the tests
	 */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sampleAccount = new Account("FR6778998", "Rostow", new BigDecimal(10));
    }
    
    @Test
    public void testFindAccountByIdThrowElementNotFoundException() throws Exception{
    	//Mocking the accountDao method
    	when(accountDaoMock.findById(1L)).thenReturn(Optional.empty());
    	try{
    		accountServiceImpl.findAccountById(1L);
		}
		catch (Exception e) {
			assertThat(e)
			.isInstanceOf(ElementNotFoundException.class)
            .hasMessage("Account not found");
		}   	
    }
    
    @Test
    public void testFindAccountByIdReturnTheAccount() throws Exception{
    	//Mocking the accountDao method
    	when(accountDaoMock.findById(1L)).thenReturn(Optional.of(sampleAccount));
    	Optional<Account> accountFound = accountServiceImpl.findAccountById(1L);
    	//making assertion
    	verify(accountDaoMock, times(1)).findById(1L);
    	assertThat(accountFound.isPresent());
    	assertThat(accountFound.get().getAccountNumber()).isEqualTo("FR6778998");
    	assertThat(accountFound.get().getAccountOwner()).isEqualTo("Rostow");
    	assertThat(accountFound.get().getCurrentBalance().compareTo(new BigDecimal(10))).isEqualTo(0);
    }
    
}
