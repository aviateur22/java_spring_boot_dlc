package com.ctoutweb.dlc.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.ctoutweb.dlc.model.friend.*;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.entity.ProductUserEntity;
import com.ctoutweb.dlc.exception.custom.FriendNotFindException;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.Friend;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.FriendsRepository;
import com.ctoutweb.dlc.repository.ProductRepository;
import com.ctoutweb.dlc.repository.ProductUserRepository;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class FriendService {
	
	private final UserRepository userRepository;
	private final FriendsRepository friendRepository;
	private final ProductRepository productRepository;
	private final ProductUserRepository productUserRepository;

	public FriendService(
			UserRepository userRepository, 
			FriendsRepository friendRepository, 
			ProductRepository productRepository, 
			ProductUserRepository productUserRepository) {
		super();
		this.userRepository = userRepository;
		this.friendRepository = friendRepository;
		this.productRepository = productRepository;
		this.productUserRepository = productUserRepository;
	}

	public AddFriendResponse addFriend(AddFriendRequest request, int userId) {
		
		User findFriend = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()->new UserNotFoundException("cet utilisateur n'est pas enregistré"));
		friendRepository.findFriendByUserIdAndFriendId(userId, findFriend.getId()).ifPresent(user->{
			System.out.println(user.getIsRelationDeleted());
			if(!user.getIsRelationDeleted())throw new UserFindException(request.getEmail() + " fait déja parti de vos contacts");
		});
		
		if(findFriend.getId() == userId)  throw new UserFindException("vous ne pouvez pas vous ajouté en tant que ami");
		
		Instant createdAt = Instant.now();
		
		FriendEntity requester = FriendEntity.builder()
				.withUserId(userId)
				.withFriendId(findFriend.getId())
				.withIsFriendRequestAccepted(true)
				.withIsFriendRequestNew(true)
				.withIsRelationAccepeted(false)
				.withIsRelationDeleted(false)
				.withCreatedAt(Timestamp.from(createdAt))
				.build();
		
		FriendEntity target = FriendEntity.builder()
				.withUserId(findFriend.getId())
				.withFriendId(userId)
				.withIsFriendRequestAccepted(false)
				.withIsFriendRequestNew(true)
				.withIsRelationAccepeted(false)
				.withIsRelationDeleted(false)
				.withCreatedAt(Timestamp.from(createdAt))
				.build();		
		
		this.updateOrSaveFriend(requester);
		this.updateOrSaveFriend(target);

		return AddFriendResponse.builder()
				.withMessage(
				"la demande d'ajout est envoyée a " + findFriend.getEmail())
				.build();				
	}
	
	public UpdateFriendResponse updateFriendRelation(UpdateFriendRelationRequest request, int userId) {
		
		Instant updatedAt = Instant.now();
		
		boolean isFriendRelationAccepted = request.isFriendRequestAccepted() == true  ? true : false;
		
		if(userId == request.getFriendId()) throw new FriendNotFindException("vous ne pouvez pas mettre à jour cette relation");
		
		Friend updaterFriend = friendRepository.findFriendByUserIdAndFriendId(userId, request.getFriendId()).orElseThrow(()-> new FriendNotFindException("cet ami n'est pas rattaché à votre compte"));
		Friend findFriendOfUpdater = friendRepository.findFriendByUserIdAndFriendId(request.getFriendId(), userId).orElseThrow(()->new FriendNotFindException("cet ami n'est pas rattaché à votre compte"));		
			
		FriendEntity userUpdater = FriendEntity.builder()
				.withUserId(userId)
				.withFriendId(request.getFriendId())
				.withIsFriendRequestAccepted(request.isFriendRequestAccepted())
				.withIsFriendRequestNew(false)
				.withIsRelationAccepeted(isFriendRelationAccepted)
				.withIsRelationDeleted(false)
				.withUpdatedAt(Timestamp.from(updatedAt))
				.build();
		
		FriendEntity friendOfUpdater = FriendEntity.builder()
				.withUserId(request.getFriendId())
				.withFriendId(userId)
				.withIsFriendRequestAccepted(findFriendOfUpdater.getIsFriendRequestAccepted())
				.withIsFriendRequestNew(false)
				.withIsRelationAccepeted(isFriendRelationAccepted)
				.withIsRelationDeleted(false)
				.withUpdatedAt(Timestamp.from(updatedAt))
				.build();
		
		friendRepository.updateFriend(userUpdater);
		friendRepository.updateFriend(friendOfUpdater);
		
		if(isFriendRelationAccepted){
			this.addProductToFriend(request.getFriendId(), userId);
			this.addProductToFriend(userId, request.getFriendId());
		}
		
		String message = isFriendRelationAccepted == true ? 
					"la relation avec " + updaterFriend.getEmail() + " est validée" 
				:
					"la relation avec " + updaterFriend.getEmail() + " est annulée";
		
		return UpdateFriendResponse.builder()
				.withMessage(message)
				.build();	
	}
	
	public DeleteFriendResponse deleteFriend(int friendId, int userId) {
		if(userId == friendId) throw new FriendNotFindException("vous ne pouvez pas mettre à jour cette relation");
		
		Instant createdAt = Instant.now();
		
		Friend deleteFriend = friendRepository.findFriendByUserIdAndFriendId(userId, friendId).orElseThrow(()-> new FriendNotFindException("cet ami n'est pas rattaché à votre compte"));
		
		FriendEntity friendOfDeleter = FriendEntity.builder()
				.withUserId(friendId)
				.withFriendId(userId)
				.withIsFriendRequestAccepted(false)
				.withIsFriendRequestNew(false)
				.withIsRelationAccepeted(false)
				.withIsRelationDeleted(true)
				.withUpdatedAt(Timestamp.from(createdAt))
				.build();
		
		friendRepository.deleteFriendByUserIdAndFriendId(userId, friendId, friendOfDeleter);
		
		return DeleteFriendResponse.builder()
			.withMessage("la relation avec " + deleteFriend.getEmail() + " est supprimée")
			.build();				
	}
	
	public FindFriendsByUserIdResponse findFriendsByUserId(int userId){
		List<Friend> friends = friendRepository.findFriendsByUserId(userId);
		return FindFriendsByUserIdResponse.FindFriendsByUserIdResponseBuilder.aFindFriendsByUserIdResponse().withFriends(friends).build();
	}
	
	private void addProductToFriend(int ownerId, int friendId) {
		Instant createdAt = Instant.now();
		List<ProductUserEntity> userProducts = productRepository.findProductsByUserOwnerId(ownerId)
				.stream()
				.map(product-> ProductUserEntity.builder()
						.withCreatedAt(Timestamp.from(createdAt))
						.withProductId(product.getId())
						.withUserId(friendId)
						.build())
				.collect(Collectors.toList());
		
		productUserRepository.addProductsToFriends(userProducts);
	}
	
	private void updateOrSaveFriend(FriendEntity friendData) {
		System.out.println(friendData);
		// Vérification si l'ami a déja le requester en liaison
		friendRepository.findFriendByUserIdAndFriendId(friendData.getUserId(), friendData.getFriendId())
		.ifPresentOrElse(user->friendRepository.updateFriend(friendData), ()->friendRepository.saveFriend(friendData));
	}
}
