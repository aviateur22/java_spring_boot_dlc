package com.ctoutweb.dlc.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.ProductUserEntity;

@Repository
public class ProductUserRepositoryImp implements ProductUserRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public ProductUserRepositoryImp(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ProductUserEntity> findProductByUserId(int userId) {
		String query = "SELECT * FROM product_user WHERE user_id=?";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProductUserEntity.class), userId);
		
	}

	@Override
	public int deleteProductByUser(int userId) {		
		return 0;
	}

}
