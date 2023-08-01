package com.ctoutweb.dlc.repository;

import java.util.Optional;

import com.ctoutweb.dlc.entity.AccountEntity;
import com.ctoutweb.dlc.model.Account;

public interface AccountRepository {
	int saveAccount(AccountEntity account);
	Optional<Account> findAccountById(int id);
	Optional<Account> findAccountByUserId(int userId);
	
}