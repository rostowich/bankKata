package com.lacombedulionvert.bankkata.dao;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.lacombedulionvert.bankkata.objects.AccountOperation;

public class AccountOperationDaoImpl implements AccountOperationDao {

	private Map<Long, AccountOperation> accountOperations = new TreeMap<Long, AccountOperation>();

	public AccountOperation save(AccountOperation accountOperation) {
		Long lastKey = accountOperations.isEmpty() ? 0
				: ((TreeMap<Long, AccountOperation>) accountOperations).lastKey();
		Long accountOperationId = lastKey + 1;
		accountOperation.setId(accountOperationId);
		accountOperations.put(accountOperationId, accountOperation);
		return accountOperations.get(accountOperationId);
	}

	public Collection<AccountOperation> findAll() {
		return accountOperations.values();
	}

}
