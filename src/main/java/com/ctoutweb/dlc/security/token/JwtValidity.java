package com.ctoutweb.dlc.security.token;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtValidity {
	
	public DecodedJWT validate(DecodedJWT jwt) {		
		return jwt;
		
	}
}
