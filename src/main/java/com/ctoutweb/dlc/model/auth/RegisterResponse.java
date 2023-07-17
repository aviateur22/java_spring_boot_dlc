package com.ctoutweb.dlc.model.auth;

import com.ctoutweb.dlc.model.MessageResponse;

public class RegisterResponse extends MessageResponse {

	private RegisterResponse(Builder builder) {
		this.message = builder.message;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String message;

		private Builder() {
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public RegisterResponse build() {
			return new RegisterResponse(this);
		}
	}

}
