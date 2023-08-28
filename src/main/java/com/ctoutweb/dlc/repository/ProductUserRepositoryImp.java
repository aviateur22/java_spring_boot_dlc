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
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;

@Repository
public class ProductUserRepositoryImp extends IdKeyHolder implements ProductUserRepository {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public ProductUserRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParamJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParamJdbcTemplate = namedParamJdbcTemplate;
	}

	@Override
	public List<ProductUserEntity> findProductByUserId(int userId) {
		String query = "SELECT * FROM product_user WHERE user_id=?";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProductUserEntity.class), userId);
		
	}
	
	@Override
	@Transactional
	public void addProductsToFriends(List<ProductUserEntity> productsUsers) {
		System.out.println(productsUsers);
		String query = "INSERT INTO product_user "
				+ "(user_id, product_id, created_at) "
				+ "VALUES "
				+ "(:userId, :productId, :createdAt)";
		
		productsUsers.forEach(product->{
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(product);
			int insertRow = namedParamJdbcTemplate.update(query, sqlParam, this.keyHolder);
			
			if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("Erreur insertion produit id N° "  + product.getProductId());
		});		
	}

	@Override
	public int deleteProductByUser(ProductUserEntity productUser) {		
		String query = "DELETE FROM product_user WHERE user_id = ? AND product_id = ?";
		int deleteRow = jdbcTemplate.update(query, productUser.getUserId(), productUser.getProductId());
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression produit");
		
		return deleteRow;
	}

	@Override
	public List<ProductUserEntity> findProductByProductId(int productId) {
		String query = "SELECT * FROM product_user WHERE product_id=?";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProductUserEntity.class), productId);
	}

	@Override
	public Optional<ProductUserEntity> findProductByUserIdAndProductId(int userId, int productId) {
		try {
			String query = "SELECT * FROM product_user WHERE user_id = ? AND product_id = ? LIMIT 1";
			ProductUserEntity findProductUser = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(ProductUserEntity.class), userId, productId);
			return Optional.of(findProductUser);
		} catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}

}
