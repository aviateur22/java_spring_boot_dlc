package com.ctoutweb.dlc.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserPrincipalAutenticationToken extends AbstractAuthenticationToken {
	private final UserPrincipal userPrincipal;

	public UserPrincipalAutenticationToken(UserPrincipal userPrincipal, Collection<? extends GrantedAuthority> authorities) {
		super(userPrincipal.getAuthorities());
		this.userPrincipal = userPrincipal;		
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {		
		return userPrincipal;
	}

}
