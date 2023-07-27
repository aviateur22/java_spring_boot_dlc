package com.ctoutweb.dlc.security.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
	
	private int id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean isAccountActive;
	
	private UserPrincipal(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;
		this.authorities = builder.authorities;
		this.isAccountActive = builder.isAccountActive;
	}

	public int getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isAccountActive;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private int id;
		private String email;
		private String password;
		private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
		private boolean isAccountActive;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		public Builder withIsAccountActive(boolean isAccountActive) {
			this.isAccountActive = isAccountActive;
			return this;
		}

		public UserPrincipal build() {
			return new UserPrincipal(this);
		}
	}

}
