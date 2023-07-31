package com.ctoutweb.dlc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.RandomTextUserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RandomTextUserEntity findByUserIdAndCategoryId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
