package com.ctoutweb.dlc.repository;

import java.util.List;

import com.ctoutweb.dlc.entity.ProductUserEntity;

public interface ProductUserRepository {
	
	List<ProductUserEntity> findProductByUserId(int userId);
	int deleteProductByUser(int userId);

}
