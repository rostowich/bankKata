package com.lacombedulionvert.bankkata.services;

import java.io.PrintStream;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacombedulionvert.bankkata.controllers.AccountController;
import com.lacombedulionvert.bankkata.controllers.AccountOperationController;
import com.lacombedulionvert.bankkata.controllers.IAccountController;
import com.lacombedulionvert.bankkata.dao.AccountDaoImpl;
import com.lacombedulionvert.bankkata.dao.AccountOperationDaoImpl;
import com.lacombedulionvert.bankkata.validators.AccountOperationValidator;
import com.lacombedulionvert.bankkata.view.ApiView;

/**
 * This class is a kind of factory creating the instances of our dependent objects.
 * This is a king of dependency injection container
 * @author rostow
 *
 */
public class DIFactory {
  
	public static  Object getBean(Class theInterface) {
		Object bean = null;
		
	if(theInterface == ApiView.class) {
    	 bean = new ApiView(
    			 new Scanner(System.in),
    			 System.out,
    			 new AccountController(new ObjectMapper(), 
    					 new AccountServiceImpl(new AccountDaoImpl(), new AccountOperationDaoImpl())),
    			 new AccountOperationController(new AccountOperationValidator(), 
    					 new ObjectMapper(),
    					 new AccountOperationServiceImpl(new AccountServiceImpl(new AccountDaoImpl(), new AccountOperationDaoImpl()), 
    							 new AccountOperationDaoImpl(), new DateUtilServiceImpl()))
    			 );
     }  
     if (theInterface == Scanner.class) {
        bean = new Scanner(System.in);
     }
     if (theInterface == PrintStream.class) {
         bean = System.out;
     }
     if (theInterface == IAccountController.class) {
         bean = new AccountController(new ObjectMapper(), 
				 new AccountServiceImpl(new AccountDaoImpl(), new AccountOperationDaoImpl()));
     } 
       	          
     return bean;
	}
}
