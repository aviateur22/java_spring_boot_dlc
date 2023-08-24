package com.ctoutweb.dlc.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ctoutweb.dlc.exception.custom.ProductNotFindException;
import com.ctoutweb.dlc.helper.DateHelper;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.ProductEntity;
import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.exception.custom.FileException;
import com.ctoutweb.dlc.model.Friend;
import com.ctoutweb.dlc.model.Product;
import com.ctoutweb.dlc.model.product.DeleteProductResponse;
import com.ctoutweb.dlc.model.product.SaveProductRequest;
import com.ctoutweb.dlc.model.product.SaveProductResponse;
import com.ctoutweb.dlc.repository.FriendsRepository;
import com.ctoutweb.dlc.repository.ProductRepository;
import com.ctoutweb.dlc.repository.ProductUserRepository;
import com.ctoutweb.dlc.service.storage.StorageService;

@Service
public class ProductService {
	
	private final FriendsRepository friendRepository;
	private final ProductRepository productRepository;
	private final StorageService storageService;	
	private final ProductUserRepository productUserRepository;
	private final DateHelper dateHelper;
	

	public ProductService(
			FriendsRepository friendRepository, 
			ProductRepository productRepository, 
			StorageService storageService, 
			ProductUserRepository productUserRepository,
			DateHelper dateHelper) {
		super();
		this.friendRepository = friendRepository;
		this.productRepository = productRepository;
		this.storageService = storageService;
		this.productUserRepository = productUserRepository;
		this.dateHelper = dateHelper;
	}

	public SaveProductResponse saveProduct(SaveProductRequest productRequest, int userId) {
				
		ProductEntity product = storageService.saveFile(productRequest, userId);
		List<Friend> friends = friendRepository.findFriendByUserIdWithRelationAccepted(userId)
				.stream()				
				.map(friend-> Friend.builder()						
						.withFriendId(friend.getFriendId())
						.build())
				.collect(Collectors.toList());				
		
		// Add Owner to the friend list
		friends.add(Friend.builder().withFriendId(userId).build());
		
		int productId = productRepository.saveProduct(product, friends);
		
		return SaveProductResponse.builder()
				.withMessage("produit ajouté")
				.withProductId(productId)
				.build();
	}

	public List<Product> findProductsByUserId(int userId){
		List<Product> products = productRepository.findProductsByUserId(userId);
		
		products
        .stream()
        .forEach(product->{
			product.setImageBase64(storageService.getImageInBase64Format(product.getFileName()));
			product.setNumberOfOpenDays(Math.abs(this.dateHelper.getNumberOfDay(product.getProductOpenDate(), new Date(), TimeUnit.DAYS)));
			product.setNumberOfDayLeftBeforeExpired(Math.abs(this.dateHelper.getNumberOfDay(product.getProductEndDate(), new Date(), TimeUnit.DAYS)));
		});

		return products;
	}

	public Product findProductByUserAndProductId(int userId, int productId) {
		Product product = this.productRepository.findProductByUserIdAndProductId(userId, productId).orElseThrow(
				()->new ProductNotFindException("le produit n'est pas trouvé"));
		product.setImageBase64(storageService.getImageInBase64Format(product.getFileName()));
		product.setNumberOfOpenDays(Math.abs(this.dateHelper.getNumberOfDay(product.getProductOpenDate(), new Date(), TimeUnit.DAYS)));
		product.setNumberOfDayLeftBeforeExpired(Math.abs(this.dateHelper.getNumberOfDay(product.getProductEndDate(), new Date(), TimeUnit.DAYS)));

		System.out.println("ouverture " + product.getProductOpenDate());
		System.out.println("fin"+ product.getProductEndDate());
		return product;
	}

	public DeleteProductResponse deleteProductById(int productId, int userId) {
		ProductEntity product = productRepository.findProductById(productId).orElseThrow(()-> new FileException("ce produit n'est pas référencé"));		
		productUserRepository.findProductByUserIdAndProductId(userId, productId).orElseThrow(()-> new FileException("vous n'êtes pas rattaché à ce produit"));
		
		// find productId in product_user  > 1 alors suppression de la ligne userId - productId dans product_user
		if(productUserRepository.findProductByProductId(productId).size() > 1) {
			productUserRepository.deleteProductByUser(ProductUserEntity.builder()
					.withUserId(userId)
					.withProductId(productId)
					.build());
			return DeleteProductResponse.builder().withMessage("Le produit est supprimé").build();
		}		
		
		// find productId in product_user  == 1 alors suppression du produit dans products et suppression du fichier		
		storageService.deleteFile(product.getFileName());
		productRepository.deleteProduct(productId);
		return DeleteProductResponse.builder().withMessage("Le produit est supprimé").build();
	}
	
	public String getImageForOneProduct(int productId) {
		ProductEntity product = productRepository.findProductById(productId).orElseGet(()->ProductEntity.builder().withFileName("").build());		
		return storageService.getImageInBase64Format(product.getFileName());
	}
}
