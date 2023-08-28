package com.ctoutweb.dlc.model.product;

import com.ctoutweb.dlc.model.MessageResponse;

public class SaveProductResponse extends MessageResponse {

	private int productId;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	private SaveProductResponse(Builder builder) {
		this.message = builder.message;
		this.productId = builder.productId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String message;
		private int productId;

		private Builder() {
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withProductId(int productId) {
			this.productId = productId;
			return this;
		}

		public SaveProductResponse build() {
			return new SaveProductResponse(this);
		}
	}

}
