package com.ctoutweb.dlc.controller;

import com.ctoutweb.dlc.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.model.product.DeleteProductResponse;
import com.ctoutweb.dlc.model.product.SaveProductRequest;
import com.ctoutweb.dlc.model.product.SaveProductResponse;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.service.ProductService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/dlc/products")
public class ProductController {
	
	private final ProductService productService;
	private final AnnotationValidator<SaveProductRequest>annotationValidatorSaveProduct;


	public ProductController(ProductService productService, AnnotationValidator<SaveProductRequest> annotationValidator) {
		super();
		this.productService = productService;
		this.annotationValidatorSaveProduct = annotationValidator;
	}


	@PostMapping("")
	public ResponseEntity<SaveProductResponse> saveProduct(@ModelAttribute SaveProductRequest request, @AuthenticationPrincipal UserPrincipal user) {		
		annotationValidatorSaveProduct.validate(request);
		SaveProductResponse response =  productService.saveProduct(request, user.getId());
		return new ResponseEntity<SaveProductResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{productId}/user/{userId}")
	public ResponseEntity<Product> findProductByUserIdAndProductId(@PathVariable("productId")
	@NotNull (message = "l'identifiant du produit est obligatoire")
	@Positive (message = "l'identifiant du produit est obligatoire")
	@Min( value = 1, message = "l'identifiant du produit est obligatoire")
	int productId,
																   @PathVariable("userId")
	@NotNull (message = "l'identifiant de l'utilisateur est obligatoire")
	@Positive (message = "l'identifiant de l'utilisateur est obligatoire")
	@Min( value = 1, message = "l'identifiant de l'utilisateur est obligatoire")
	int userId) {
		Product product = productService.findProductByUserAndProductId(userId, productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> findProductsByUserId(@PathVariable("userId")
    @NotNull(message = "l'identifiant de l'utilisateur est obligatoire")
    @Positive (message = "l'identifiant de l'utilisateur est obligatoire")
    @Min(value = 1, message = "l'identifiant de l'utilisateur est obligatoire")
    int userId){
        List<Product> userProducts = this.productService.findProductsByUserId(userId);
        return new ResponseEntity<>(userProducts, HttpStatus.OK);
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
	
	@GetMapping("/{id}/image")
	public ResponseEntity<String> getProductImage(@PathVariable("id") 
	@NotNull (message = "l'identifiant du produit est obligatoire")
	@Positive (message = "l'identifiant du produit est obligatoire")
	@Min( value = 1, message = "l'identifiant du produit est obligatoire")
	int id, @AuthenticationPrincipal UserPrincipal user) {
		String image = productService.getImageForOneProduct(id);
		return new ResponseEntity<String>(image, HttpStatus.OK);
	}

}
