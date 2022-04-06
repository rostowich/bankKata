package com.lacombedulionvert.bankkata.objects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountOperation {

	private Long id;

	private LocalDateTime operationDate;

	private OperationType operationType;

	private BigDecimal amount;

	private BigDecimal accountBalance;

	private Account account;

	public AccountOperation(OperationType operationType, BigDecimal amount) {
		super();
		this.operationType = operationType;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(LocalDateTime operationDate) {
		this.operationDate = operationDate;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getAccountCurrentBalance() {

		return (operationType.compareTo(OperationType.DEPOSIT) == 0) ? amount.add(accountBalance)
				: accountBalance.subtract(amount);
	}

}
