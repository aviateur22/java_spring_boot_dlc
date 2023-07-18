package com.ctoutweb.dlc.service.storage;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.model.product.SaveProductRequest;

public interface StorageService {
	public ProductEntity saveFile(SaveProductRequest productRequest, int userId);
	public void deleteFile(String fileName);
	public String getImageInBase64Format(String fileName);
}
