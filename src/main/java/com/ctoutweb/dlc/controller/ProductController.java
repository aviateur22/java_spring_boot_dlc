package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.model.product.DeleteProductResponse;
import com.ctoutweb.dlc.model.product.SaveProductRequest;
import com.ctoutweb.dlc.model.product.SaveProductResponse;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.service.ProductService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	private final ProductService productService;
	private final AnnotationValidator<SaveProductRequest>annotationValidatorSaveProduct;


	public ProductController(ProductService productService, AnnotationValidator<SaveProductRequest> annotationValidator) {
		super();
		this.productService = productService;
		this.annotationValidatorSaveProduct = annotationValidator;
	}


	@PostMapping("/")
	public ResponseEntity<SaveProductResponse> saveProduct(@ModelAttribute SaveProductRequest request, @AuthenticationPrincipal UserPrincipal user) {		
		annotationValidatorSaveProduct.validate(request);
		
		SaveProductResponse response =  productService.saveProduct(request, user.getId());
		return new ResponseEntity<SaveProductResponse>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("id")
	@NotNull (message = "l'identifiant du produit est obligatoire")
	@Positive (message = "l'identifiant du produit est obligatoire")
	@Min( value = 1, message = "l'identifiant du produit est obligatoire")
	int id, @AuthenticationPrincipal UserPrincipal user){
		DeleteProductResponse response = productService.deleteProductById(id, user.getId());
		return new ResponseEntity<DeleteProductResponse>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> getProductImage(){
		return null;
	}

}
