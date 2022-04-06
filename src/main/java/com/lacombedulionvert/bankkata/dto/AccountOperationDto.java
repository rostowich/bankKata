package com.lacombedulionvert.bankkata.dto;

public class AccountOperationDto {

	private String accountId;

	private String amount;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AccountOperationDto [accountId=" + accountId + ", amount=" + amount + "]";
	}

}
