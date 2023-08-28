package com.ctoutweb.dlc.model.product;

import com.ctoutweb.dlc.model.MessageResponse;

public class DeleteProductResponse extends MessageResponse {

		private DeleteProductResponse(Builder builder) {
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

		public DeleteProductResponse build() {
			return new DeleteProductResponse(this);
		}
	}
	
}
