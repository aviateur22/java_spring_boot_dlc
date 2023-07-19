package com.ctoutweb.dlc.repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.TokenEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;

@Repository
public class TokenRepositoryImp extends IdKeyHolder implements TokenRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public TokenRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int saveToken(TokenEntity token) {
		String query = "INSERT INTO tokens (user_id, token, expired_at, is_valid, created_at) VALUES (:userId, :token, :expiredAt, :isValid, :createdAt)";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(token);		
		int insertRow = namedParameterJdbcTemplate.update(query, sqlParam, this.keyHolder);
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("erreur insertion token");
		return insertRow;
	}

	@Override
	public Optional<TokenEntity> findTokenByUserIdAndToken(TokenEntity token) {
		try {			
			String query = "SELECT * FROM tokens WHERE user_id = ? AND token = ? LIMIT 1";
			TokenEntity findToken = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(TokenEntity.class), token.getUserId(), token.getToken());			
			return Optional.of(findToken);
		} catch (IncorrectResultSizeDataAccessException e) {		
			return Optional.empty();
		}
	}

	@Override
	public Optional<TokenEntity> findTokenByUserId(int userId) {
		try {
			String query = "SELECT * FROM tokens WHERE user_id = ? LIMIT 1";
			TokenEntity findToken = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(TokenEntity.class), userId);
			return Optional.of(findToken);
			
		} catch (Exception e) {
			return Optional.empty();
		}	
	}
	
	@Override
	public int updateToken(TokenEntity token) {
		Instant updatedAt = Instant.now();
		token.setUpdatedAt(Timestamp.from(updatedAt));
		System.out.println(token);
		String query = "UPDATE tokens "
				+ "SET "				
				+ "token = :token, "
				+ "expired_at = :expiredAt, "
				+ "is_valid = :isValid, "
				+ "created_at = :createdAt, "
				+ "updated_at = :updatedAt "
				+ "WHERE user_id = :userId";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(token);		
		int updatedRow = namedParameterJdbcTemplate.update(query, sqlParam, this.keyHolder);
		if(this.isKeyHolderOrInsertRowUnvalid(updatedRow)) throw new InsertSQLException("erreur insertion token");
		return updatedRow;
	}

	@Override
	public int deleteTokenByUserId(int userId) {
		String query = "DELETE FROM tokens WHERE user_id=?";
		int rowDelete = jdbcTemplate.update(query, userId);
		
		if(rowDelete == 0) throw new InsertSQLException("erreur suppression token");
		
		return rowDelete;
	}


}
