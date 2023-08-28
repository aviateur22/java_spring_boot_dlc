package com.ctoutweb.dlc.model.auth;

import com.ctoutweb.dlc.model.MessageResponse;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class LoginResponse extends MessageResponse {
	private String token;
	private List<String> roles;
	private String email;
	private int id;

	public LoginResponse(String token) {
		this.token = token;
	}

	public LoginResponse(LoginResponseBuilder builder) {
		this.email = builder.email;
		this.roles = builder.roles;
		this.token = builder.token;
		this.message = builder.message;
		this.id = builder.id;
	}

	public String getToken() {
		return token;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static final class LoginResponseBuilder {
		private String message;
		private String token;
		private List<String> roles;
		private String email;
		private int id;

		public LoginResponseBuilder() {
		}

		public static LoginResponseBuilder aLoginResponse() {
			return new LoginResponseBuilder();
		}

		public LoginResponseBuilder withMessage(String message) {
			this.message = message;
			return this;
		}

		public LoginResponseBuilder withToken(String token) {
			this.token = token;
			return this;
		}

		public LoginResponseBuilder withRoles(List<String> roles) {
			this.roles = roles;
			return this;
		}

		public LoginResponseBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public LoginResponseBuilder withId(int id) {
			this.id = id;
			return this;
		}

		public LoginResponse build() {
			return new LoginResponse(this);
		}
	}
}
