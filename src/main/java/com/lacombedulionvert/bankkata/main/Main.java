package com.lacombedulionvert.bankkata.main;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lacombedulionvert.bankkata.dao.AccountDao;
import com.lacombedulionvert.bankkata.dao.AccountDaoImpl;
import com.lacombedulionvert.bankkata.objects.Account;
import com.lacombedulionvert.bankkata.services.DIFactory;
import com.lacombedulionvert.bankkata.view.ApiView;


public class Main {

	public static void main(String[] args) throws NumberFormatException, JsonProcessingException {
		//Initialize the bean context and get an instance of the controller from the context factory
    	ApiView view = (ApiView) DIFactory.getBean(ApiView.class);
    	_createSampleAccount();
        //start the game    	
    	view.startBankKata();
	}
	
	private static void _createSampleAccount() {
		AccountDao accountDao = new AccountDaoImpl();
		Account sampleAccount = new Account("FR7845673462657", "Rostow GOKENG", new BigDecimal(0));
		Account accountSaved = accountDao.save(sampleAccount);
		System.out.println("A first account is saved with those parameters: "+accountSaved);
	}
}
