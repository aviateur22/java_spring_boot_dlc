package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.Friend;
import com.ctoutweb.dlc.model.Product;

@Repository
public class ProductRepositoryImp extends IdKeyHolder implements ProductRepository {
	private final JdbcTemplate jdbcTemplate;
	private final ProductUserRepository productUserRepository;
	private final NamedParameterJdbcTemplate namedParamJdbcTemplate;

	public ProductRepositoryImp(JdbcTemplate jdbcTemplate, ProductUserRepository productUserRepository, NamedParameterJdbcTemplate namedParamJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.productUserRepository = productUserRepository;
		this.namedParamJdbcTemplate = namedParamJdbcTemplate;
	}

	@Override
	public List<Product> findProductsByUserId(int userId) {
		
		List<Integer> productListId = productUserRepository.findProductByUserId(userId)
				.stream()
				.map(result-> result.getProductId())
				.collect(Collectors.toList());
		
		if (productListId.size() == 0) return List.of();
		
		String query = "SELECT * FROM products WHERE products.id IN (:ids)";
		SqlParameterSource sqlParam = new MapSqlParameterSource("ids", productListId);
		
		return namedParamJdbcTemplate.query(query, sqlParam, BeanPropertyRowMapper.newInstance(Product.class));
		
	}

	@Override
	@Transactional
	public int saveProduct(ProductEntity product, List<Friend> friends) {
		
		String query = "INSERT INTO products "
				+ "(path, file_extension, file_name, product_end_date, product_open_date, user_id, created_at) "
				+ "VALUES "
				+ "(:path, :fileExtension, :fileName, :productEndDate, :productOpenDate, :userId, :createdAt)";
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(product);
		
		int insertedRow = namedParamJdbcTemplate.update(query, sqlParam, this.keyHolder);
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertedRow)) throw new InsertSQLException("erreur insertion nouveau produit");	
		
		int productId = this.getKeyHolderId();
		
		List<ProductUserEntity> productUser = friends
				.stream()
				.map(friend-> ProductUserEntity.builder()
						.withProductId(productId)
						.withUserId(friend.getFriendId())
						.withCreatedAt(product.getCreatedAt())
						.build())
				.collect(Collectors.toList());
		
		productUserRepository.addProductsToFriends(productUser);
		
		return productId;
	}

	@Override
	public int deleteProduct(int productId) {
		
		String query = "DELETE FROM products WHERE id = ?";
		int deleteRow = jdbcTemplate.update(query,productId);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression produit");
		
		return deleteRow;
	}

	@Override
	public List<Product> findProductsByUserOwnerId(int userId) {		
		String query = "SELECT * FROM products WHERE user_id = ?";		
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Product.class), userId);
	}

	@Override
	public Optional<ProductEntity> findProductById(int productId) {
		try {
			String query = "SELECT * FROM products WHERE id=? LIMIT 1";
			ProductEntity product = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(ProductEntity.class), productId);
			return Optional.of(product);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}	
	}
}
