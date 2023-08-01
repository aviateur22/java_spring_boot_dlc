package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.RandomTextUserEntity;
import com.ctoutweb.dlc.model.RandomTextUser;

public interface RandomTextUserRepository {
	int save(RandomTextUserEntity randomTextUser);
	int delete(int id);
	int deleteByUserId(int userId);
	Optional<RandomTextUserEntity> findByUserIdAndCategoryId(int userId, int categoryId);
	List<RandomTextUser> findByUserI(int userId);
}
