package com.ctoutweb.dlc.repository;

import java.util.Optional;

import com.ctoutweb.dlc.entity.TokenEntity;

public interface TokenRepository {
	int saveToken(TokenEntity token);
	Optional<TokenEntity> findTokenByUserIdAndToken(TokenEntity token);
	Optional<TokenEntity> findTokenByUserId(int userId);
	int updateToken(TokenEntity token);
	int deleteTokenByUserId(int userId);
}
