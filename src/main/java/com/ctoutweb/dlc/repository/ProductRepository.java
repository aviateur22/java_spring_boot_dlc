package com.ctoutweb.dlc.repository;

import java.util.List;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.model.Product;

public interface ProductRepository {
	
	List<Product> findProductByUserId(int userId);
	int saveProduct(ProductEntity product, List<ProductUserEntity> productUser);
	int deleteProduct(ProductEntity product, List<ProductUserEntity> productUser);
}
