package com.lacombedulionvert.bankkata.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
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

	public Optional<AccountOperation> getLastAccountOperation() {
		if (accountOperations.isEmpty())
			return Optional.empty();
		
		Long lastKey = ((TreeMap<Long, AccountOperation>) accountOperations).lastKey();
		return Optional.of(accountOperations.get(lastKey));

	}

	public Collection<AccountOperation> findAll(Long acccountId) {
		Collection<AccountOperation> result = new ArrayList<AccountOperation>();
		Collection<AccountOperation> operations = accountOperations.values();
		Iterator<AccountOperation> iterator = operations.iterator();
		while (iterator.hasNext()) {
			AccountOperation accountOperation = (AccountOperation) iterator.next();
			if(accountOperation.getAccount().getId() == acccountId)
				result.add(accountOperation);
		}
		return result;
	}

}
