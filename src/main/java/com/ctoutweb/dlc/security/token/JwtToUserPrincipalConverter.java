package com.ctoutweb.dlc.security.token;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.dlc.security.UserPrincipal;

@Component
public class JwtToUserPrincipalConverter {
	
	public UserPrincipal convert(DecodedJWT jwt) {
		List<SimpleGrantedAuthority> authorities = getGrantedAuthoritiesFromClaim(jwt);
		
		return UserPrincipal.builder()
				.withId(jwt.getClaim("id").asInt())
				.withAuthorities(authorities)
				.withEmail(jwt.getSubject())
				.build();
	}
	
	private List<SimpleGrantedAuthority> getGrantedAuthoritiesFromClaim(DecodedJWT jwt) {
		var claim = jwt.getClaim("authorities");
		
		if(claim.isNull() || claim.isMissing()) {
			return List.of();
		}
		
		return claim.asList(SimpleGrantedAuthority.class);
	}

}
