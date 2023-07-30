package com.ctoutweb.dlc.repository;

import com.ctoutweb.dlc.entity.RandomTextUserEntity;

public interface RandomTextUserRepository {
	int save(RandomTextUserEntity randomTextUser);
	int delete(int id);
	RandomTextUserEntity findByUserIdAndCategoryId(int id);
}
