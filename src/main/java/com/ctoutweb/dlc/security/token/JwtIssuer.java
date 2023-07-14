package com.ctoutweb.dlc.security.token;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ctoutweb.dlc.properties.JwtProperties;
import com.ctoutweb.dlc.security.UserPrincipal;

@Component
public class JwtIssuer {
	private final JwtProperties jwtProperties;

	public JwtIssuer(JwtProperties jwtProperties) {
		super();
		this.jwtProperties = jwtProperties;
	}
	
	public String issue(UserPrincipal user) {
		Instant expiredAt = Instant.now().plus(Duration.ofHours(3));
		byte[] timeNow = ("time now" +" " + System.currentTimeMillis()).getBytes();
		List<String> authorities = user.getAuthorities().stream().map(auth->auth.toString()).collect(Collectors.toList());
		
		return JWT.create()
				.withSubject(user.getUsername())
				.withJWTId(UUID.nameUUIDFromBytes(timeNow).toString())
				.withIssuer(jwtProperties.getIssuer())
				.withExpiresAt(expiredAt)
				.withClaim("id", user.getId())
				.withClaim("authorities", authorities)
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
				
	}
}
