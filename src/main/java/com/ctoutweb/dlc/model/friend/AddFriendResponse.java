package com.ctoutweb.dlc.model.friend;

import com.ctoutweb.dlc.model.MessageResponse;

public class AddFriendResponse extends MessageResponse {
	
	public AddFriendResponse() {
		
	}

	private AddFriendResponse(Builder builder) {
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

		public AddFriendResponse build() {
			return new AddFriendResponse(this);
		}
	}
	
}
