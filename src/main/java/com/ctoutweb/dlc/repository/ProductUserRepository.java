package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.ProductUserEntity;

public interface ProductUserRepository {
	
	List<ProductUserEntity> findProductByUserId(int userId);
	List<ProductUserEntity> findProductByProductId(int productId);
	void addProductsToFriends(List<ProductUserEntity> productsUsers);
	int deleteProductByUser(ProductUserEntity productUser);
	Optional<ProductUserEntity> findProductByUserIdAndProductId(int userId, int productId);

}
