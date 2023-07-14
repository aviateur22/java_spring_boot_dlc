package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.model.Product;

@Repository
public class ProductRepositoryImp implements ProductRepository {
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
	public List<Product> findProductByUserId(int userId) {
		
		List<Integer> productListId = productUserRepository.findProductByUserId(userId)
				.stream()
				.map(result-> result.getProduct_id())
				.collect(Collectors.toList());
		
		if (productListId.size() == 0) return List.of();
		
		String query = "SELECT * FROM products WHERE products.id IN (:ids)";
		SqlParameterSource sqlParam = new MapSqlParameterSource("ids", productListId);
		
		return namedParamJdbcTemplate.query(query, sqlParam, BeanPropertyRowMapper.newInstance(Product.class));
		
	}

	@Override
	public int saveProduct(ProductEntity product, List<ProductUserEntity> productUser) {
		
		return 0;
	}

	@Override
	public int deleteProduct(ProductEntity product, List<ProductUserEntity> productUser) {
		
		return 0;
	}

}
