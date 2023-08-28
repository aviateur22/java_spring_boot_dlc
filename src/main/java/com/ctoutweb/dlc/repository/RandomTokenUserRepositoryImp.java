package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.RandomTokenUserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.RandomConfirmationToken;

@Repository
public class RandomTokenUserRepositoryImp extends IdKeyHolder implements RandomTokenUserRepository {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	public RandomTokenUserRepositoryImp(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int save(RandomTokenUserEntity randomTextUser) {
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(randomTextUser);		
		String userQuery = "INSERT INTO random_token_user (random_text, iv, user_id, random_category_id) VALUES (:randomText, :iv, :userId, :categoryId)";		
		int insertRow = namedParameterJdbcTemplate.update(userQuery, sqlParam, this.keyHolder);		
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("probleme insertion user");		
		
		return this.getKeyHolderId();
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM random_token_user WHERE id = ?";
		int deleteRow = jdbcTemplate.update(query, id);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression randomToken");		
		return deleteRow;
	}

	@Override
	public Optional<RandomTokenUserEntity> findByUserIdAndCategoryId(int userId, int categoryId) {
		try {
			String query = "SELECT * FROM random_token_user WHERE user_id = ? AND random_category_id = ?";
			RandomTokenUserEntity findRandomText = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(RandomTokenUserEntity.class), userId, categoryId);
			return Optional.of(findRandomText);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
		
	}

	@Override
	public List<RandomConfirmationToken> findByUserI(int userId) {
		String query = "SELECT rtu.id, rtu.random_text, rtu.iv, rtu.expired_at, rtc.id AS rtcId, rtc.category "
				+ "FROM random_token_user AS rtu "
				+ "JOIN random_token_categories AS rtc "
				+ "ON rtu.random_category_id = rtc.id "
				+ "WHERE rtu.user_id = ?";
		
		List<RandomConfirmationToken> findUserRandomText = jdbcTemplate.query(query, 
				(rs, rowNum)-> RandomConfirmationToken.builder()
				.withId(rs.getInt("id"))
				.withCategory(rs.getString("category"))
				.withCategoryId(rs.getInt("rtcId"))
				.withIv(rs.getString("iv"))
				.withRandomText(rs.getString("random_text"))
				.withExpiredAt(rs.getTimestamp("expired_at"))
				.build(),
				userId);
		return findUserRandomText;
	}

	@Override
	public int deleteByUserId(int userId) {
		String query = "DELETE FROM random_token_user WHERE user_id = ?";
		int deleteRow = jdbcTemplate.update(query, userId);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression randomToken");		
		return deleteRow;
	}

	@Override
	public int deleteByTokenCategoryIdAndUserId(int userId, int tokenCategoryId) {
		String query = "DELETE FROM random_token_user WHERE user_id = ? AND random_category_id = ?";
		int deleteRow = jdbcTemplate.update(query, userId, tokenCategoryId);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression randomToken");		
		return deleteRow;
	}

}
