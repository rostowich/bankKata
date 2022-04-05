package com.lacombedulionvert.bankkata.dao;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.lacombedulionvert.bankkata.objects.Account;

public class AccountDaoImpl implements AccountDao {

	private Map<Long, Account> accounts = new TreeMap<Long, Account>();

	public Account save(Account account) {
		Long lastKey = accounts.isEmpty() ? 0 : ((TreeMap<Long, Account>) accounts).lastKey();
		Long accountId = lastKey+1;
		account.setId(accountId);
		accounts.put(accountId, account);
		return accounts.get(accountId);
	}

	public Account update(Long id, Account account) {
		accounts.put(id, account);
		return accounts.get(id);
	}

	public Optional<Account> findById(Long id) {
		if(accounts.containsKey(id))
			return Optional.of(accounts.get(id));
		
		return Optional.empty();
	}
	
	

}
