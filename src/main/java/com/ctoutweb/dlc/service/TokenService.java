package com.ctoutweb.dlc.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.TokenEntity;
import com.ctoutweb.dlc.exception.custom.TokenException;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.repository.TokenRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class TokenService {
	private final TokenRepository tokenRepository;

	public TokenService(TokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
	}
	
	public void saveToken(int userId, TokenIssue tokenIssue) {
		
		Instant createdAt = Instant.now();
		TokenEntity tokenEntity = TokenEntity.builder()
				.withToken(tokenIssue.getToken())
				.withUserId(userId)
				.withIsValid(true)
				.withExpiredAt(tokenIssue.getExpiredAt())
				.withCreatedAt(Timestamp.from(createdAt))
				.build();
		
		tokenRepository.findTokenByUserId(userId).ifPresentOrElse((token)->tokenRepository.updateToken(tokenEntity),()->tokenRepository.saveToken(tokenEntity));		
	}
	
	public void validateToken(String token,  int userId) {
		
		if(!StringUtils.isNotBlank(token) || userId <= 0) throw new TokenException("ce token n'est pas valide");		
		TokenEntity tokenEntity = TokenEntity.builder().withUserId(userId).withToken(token).build();		
		TokenEntity findToken = tokenRepository.findTokenByUserIdAndToken(tokenEntity).orElseThrow(()-> new TokenException("ce token n'est pas valide"));
		
		if(!findToken.getIsValid()) new TokenException("ce token n'est pas valide");
	}
	
	public void deleteToken(int userId) {		
		System.out.println("ic");
		tokenRepository.findTokenByUserId(userId).ifPresentOrElse((findToken)->tokenRepository.deleteTokenByUserId(userId), null);		
	}

}
