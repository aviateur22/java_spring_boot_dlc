package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.product.DeleteProductResponse;
import com.ctoutweb.dlc.model.product.SaveProductRequest;
import com.ctoutweb.dlc.model.product.SaveProductResponse;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.service.ProductService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	private final ProductService productService;


	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}


	@PostMapping("/")
	public ResponseEntity<SaveProductResponse> saveProduct(@ModelAttribute SaveProductRequest request, @AuthenticationPrincipal UserPrincipal user) {		
		SaveProductResponse response =  productService.saveProduct(request, user.getId());
		return new ResponseEntity<SaveProductResponse>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("id") @NotNull @NotBlank int id, @AuthenticationPrincipal UserPrincipal user){
		DeleteProductResponse response = productService.deleteProductById(id, user.getId());
		return new ResponseEntity<DeleteProductResponse>(response, HttpStatus.OK);
	}

}
