package com.ctoutweb.dlc.model.auth;

import com.ctoutweb.dlc.model.MessageResponse;

public class LogoutResponse extends MessageResponse {

	private LogoutResponse(Builder builder) {
		this.message = builder.message;
	}

	public LogoutResponse(String message) {
		
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

		public LogoutResponse build() {
			return new LogoutResponse(this);
		}
	}
	
}
