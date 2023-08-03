package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.RandomTokenUserEntity;
import com.ctoutweb.dlc.model.RandomConfirmationToken;

public interface RandomTokenUserRepository {
	int save(RandomTokenUserEntity randomTextUser);
	int delete(int id);
	int deleteByUserId(int userId);
	int deleteByTokenCategoryIdAndUserId(int userId, int tokenCategoryId);
	Optional<RandomTokenUserEntity> findByUserIdAndCategoryId(int userId, int categoryId);
	List<RandomConfirmationToken> findByUserI(int userId);
}
