package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.model.Friend;
import com.ctoutweb.dlc.model.Product;

public interface ProductRepository {
	
	List<Product> findProductsByUserId(int userId);
	List<Product> findProductsByUserOwnerId(int userId);
	Optional<ProductEntity> findProductById(int productId);
	Optional<Product> findProductByUserIdAndProductId(int userId, int productId);
	int saveProduct(ProductEntity product, List<Friend> friends);
	int deleteProduct(int productId);
}
