package com.ctoutweb.dlc.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserPrincipalAutenticationToken extends AbstractAuthenticationToken {
	private final UserPrincipal userPrincipal;

	public UserPrincipalAutenticationToken(UserPrincipal userPrincipal) {
		super(userPrincipal.getAuthorities());
		this.userPrincipal = userPrincipal;
		setAuthenticated(true);
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
