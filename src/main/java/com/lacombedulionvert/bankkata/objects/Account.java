package com.lacombedulionvert.bankkata.objects;

import java.math.BigDecimal;

public class Account {

	private Long id;

	private String accountNumber;

	private String accountOwner;

	private BigDecimal currentBalance;

	public Account(String accountNumber, String accountOwner, BigDecimal currentBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountOwner = accountOwner;
		this.currentBalance = currentBalance;
	}

	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountOwner=" + accountOwner
				+ ", currentBalance=" + currentBalance + "]";
	}

}
