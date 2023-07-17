package com.ctoutweb.dlc.model.product;

import java.util.Objects;

public class DeleteProductRequest {
	private int productId;

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeleteProductRequest other = (DeleteProductRequest) obj;
		return productId == other.productId;
	}

	@Override
	public String toString() {
		return "DeleteProductRequest [productId=" + productId + "]";
	}
	
	
}
