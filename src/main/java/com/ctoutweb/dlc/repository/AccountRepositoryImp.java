package com.ctoutweb.dlc.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.AccountEntity;
import com.ctoutweb.dlc.model.Account;

@Repository
public class AccountRepositoryImp implements AccountRepository {

	@Override
	public int saveAccount(AccountEntity account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Account> findAccountById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
