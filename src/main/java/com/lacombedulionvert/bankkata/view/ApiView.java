package com.lacombedulionvert.bankkata.view;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lacombedulionvert.bankkata.controllers.IAccountController;
import com.lacombedulionvert.bankkata.controllers.IAccountOperationController;
import com.lacombedulionvert.bankkata.dto.AccountOperationDto;

public class ApiView {

	private Scanner scanner;
	
    private PrintStream printStream;
    
    private IAccountController accountController;
    
    private IAccountOperationController accountOperationController;
    
    private static final String API_PATTERN = "(\\s*(DEPOSIT|WITHDRAWAL)\\s+\\d+\\s+\\d+\\s*)|"
			+ "(\\s*(HISTORY|CHECK)\\s+\\d+\\s*)|"
			+ "(\\s*(EXIT)\\s*)";
    
    public ApiView(Scanner scanner, PrintStream printStream, IAccountController accountController,
			IAccountOperationController accountOperationController) {
		this.scanner = scanner;
		this.printStream = printStream;
		this.accountController = accountController;
		this.accountOperationController = accountOperationController;
	}
    
    private boolean _isValidApi(String api) {
    	 return Pattern.compile(API_PATTERN).matcher(api).matches();
    }
    
    private boolean _isDeposit(String command) {
    	return command.startsWith("DEPOSIT");
    }
    
    private boolean _isWithdrawal(String command) {
    	return command.startsWith("WITHDRAWAL");
    }
    
    private boolean _isHistoty(String command) {
    	return command.startsWith("HISTORY");
    }
    
    private boolean _isCheck(String command) {
    	return command.startsWith("CHECK");
    }
    
    private boolean _isExit(String command) {
    	return command.startsWith("EXIT");
    }

	private String _readAndCheckValue(String prompt) {
		printStream.println(prompt);
        String value = "";
        boolean tryAgain;

        do {
            try {
                value = scanner.nextLine();
                if (!_isValidApi(value))
                    throw new InputMismatchException("Please enter a valid API CALL.");
                tryAgain = false;
            } catch (InputMismatchException exception) {
            	scanner.nextLine();
            	printStream.println(exception.getMessage());
            	printStream.println(prompt);
                tryAgain = true;
            }
        }
        while (tryAgain);

        return value;
    }
    
    public void startBankKata() throws NumberFormatException, JsonProcessingException {
    	printStream.println("********************************************************************");
    	printStream.println("********** WELCOME TO BANK KATA. LET'S START  ************");
    	printStream.println(" A sample account is created with the ID=1 and the balance 0");
    	printStream.println(" With the application you can make 4 operations ");
    	printStream.println("For deposit, use the command: 'DEPOSIT 1 150' where 1 is the account ID and 150 the amount");
    	printStream.println("For withdrawal, use the command: 'WITHDRAWAL 1 150' where 1 is the account ID and 150 the amount");
    	printStream.println("For history, use the command: 'HISTORY 1' where 1 is the account ID ");
    	printStream.println("For statement of the account, use the command: 'CHECK 1' where 1 is the account number ");
    	printStream.println("To stop the application use the command 'EXIT'");
    	
    	printStream.println("********************************************************************");
    	printStream.println("                 ");
        String command = _readAndCheckValue("Proceed with the command?");
       
        boolean endOfTheGame = false;
        String result ="";
        AccountOperationDto accountOperationDto = new AccountOperationDto();
        do {
        	if(_isExit(command)) {
        		endOfTheGame = true;
        	}else if(_isDeposit(command)){
        		String[] parameters = command.split("\\s+");
        		accountOperationDto.setAccountId(parameters[1]);
        		accountOperationDto.setAmount(parameters[2]);
        		result = accountOperationController.saveAmount(accountOperationDto);
        		printStream.println(result);
        	}else if(_isWithdrawal(command)){
        		String[] parameters = command.split("\\s+");
        		accountOperationDto.setAccountId(parameters[1]);
        		accountOperationDto.setAmount(parameters[2]);
        		result = accountOperationController.retrieveAmount(accountOperationDto);
        		printStream.println(result);
        	}else if(_isCheck(command)){
        		String[] parameters = command.split("\\s+");
        		result = accountController.accountStatement(Long.parseLong((parameters[1])));
        		printStream.println(result);
        	}
        	else if(_isHistoty(command)){
        		String[] parameters = command.split("\\s+");
        		result = accountOperationController.checkOperations(Long.parseLong(parameters[1]));
        		printStream.println(result);
        	}   
        	command = _readAndCheckValue("Proceed with the command?");
        }
        while (!endOfTheGame);

        	printStream.println("####################");
        	printStream.println("# You won. Perfect #");
        	printStream.println("####################");
        
    }
}
