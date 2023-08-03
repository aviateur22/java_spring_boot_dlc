package com.ctoutweb.dlc.repository;

import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.AccountEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.Account;

@Repository
public class AccountRepositoryImp extends IdKeyHolder implements AccountRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;	

	public AccountRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int saveAccount(AccountEntity account) {
		String query = "INSERT INTO accounts (password, user_id) VALUES (:password, :userId)";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(account);
		int insertRow = namedParameterJdbcTemplate.update(query, sqlParam, keyHolder);
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("probleme insertion account");
		
		return this.getKeyHolderId();
	}

	@Override
	public Optional<Account> findAccountById(int id) {
		try {
			String query = "SELECT * FROM accounts WHERE id = ?";
			Account account = jdbcTemplate.queryForObject(query,BeanPropertyRowMapper.newInstance(Account.class), id);
			return Optional.of(account);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Account> findAccountByUserId(int userId) {
		try {
			String query = "SELECT * FROM accounts WHERE user_id = ?";
			Account account = jdbcTemplate.queryForObject(query,BeanPropertyRowMapper.newInstance(Account.class), userId);
			return Optional.of(account);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public int deleteAccountByUserId(int userId) {
		String query = "DELETE FROM accounts WHERE user_id = ?";
		int deleteRow = jdbcTemplate.update(query, userId);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression compte utilisateur");		
		return deleteRow;
	}

	@Override
	public int updateAccountByUserId(AccountEntity account) {
		String query = "UPDATE accounts SET is_account_active=:isAccountActive, account_activation_at=:accountActivationAt, updated_at=:updatedAt WHERE user_id=:userId";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(account);
		int updatedRow = namedParameterJdbcTemplate.update(query, sqlParam);
		
		if(updatedRow == 0) throw new InsertSQLException("probleme update account");
		
		return updatedRow;
	}
	
	

}
