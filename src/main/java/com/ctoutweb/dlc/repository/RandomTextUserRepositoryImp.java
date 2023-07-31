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

import com.ctoutweb.dlc.entity.RandomTextUserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.RandomTextUser;
import com.ctoutweb.dlc.model.User;

@Repository
public class RandomTextUserRepositoryImp extends IdKeyHolder implements RandomTextUserRepository {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	public RandomTextUserRepositoryImp(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int save(RandomTextUserEntity randomTextUser) {
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(randomTextUser);		
		String userQuery = "INSERT INTO random_text_user (random_text, iv, user_id, random_category_id) VALUES (:randomText, :iv, :userId, :categoryId)";		
		int insertRow = namedParameterJdbcTemplate.update(userQuery, sqlParam, this.keyHolder);		
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("probleme insertion user");		
		
		return this.getKeyHolderId();
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM random_text_user WHERE id = ?";
		int deleteRow = jdbcTemplate.update(query, id);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression randomText");		
		return deleteRow;
	}

	@Override
	public Optional<RandomTextUserEntity> findByUserIdAndCategoryId(int userId, int categoryId) {
		try {
			String query = "SELECT * FROM random_text_user WHERE user_id = ? AND random_category_id = ?";
			RandomTextUserEntity findRandomText = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(RandomTextUserEntity.class), userId, categoryId);
			return Optional.of(findRandomText);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
		
	}

	@Override
	public List<RandomTextUser> findByUserI(int userId) {
		String query = "SELECT rtu.id, rtu.random_text, rtu.iv, rtu.expired_at, rtc.id AS rtcId, rtc.category "
				+ "FROM random_text_user AS rtu "
				+ "JOIN random_text_categories AS rtc "
				+ "ON rtu.random_category_id = rtc.id "
				+ "WHERE rtu.user_id = ?";
		
		List<RandomTextUser> findUserRandomText = jdbcTemplate.query(query, 
				(rs, rowNum)-> RandomTextUser.builder()
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

}
