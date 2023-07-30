package com.ctoutweb.dlc.model.auth;

import com.ctoutweb.dlc.model.MessageResponse;

public class RegisterEmailResponse extends MessageResponse {

	private int userId;
	
	private RegisterEmailResponse(Builder builder) {
		this.message = builder.message;
		this.userId = builder.userId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static final class Builder {
		private String message;
		private int userId;

		private Builder() {
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public RegisterEmailResponse build() {
			return new RegisterEmailResponse(this);
		}
	}

}
