package com.ctoutweb.dlc.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.AddFriendRequest;
import com.ctoutweb.dlc.model.AddFriendResponse;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.FriendsRepository;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class FriendService {
	
	private final UserRepository userRepository;
	private final FriendsRepository friendRepository;

	public FriendService(UserRepository userRepository, FriendsRepository friendRepository) {
		super();
		this.userRepository = userRepository;
		this.friendRepository = friendRepository;
	}

	public AddFriendResponse addFriendService(AddFriendRequest request, int userId) {
		
		User findFriend = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()->new UserNotFoundException("Cet utilisateur n'est pas enregistré"));
		
		Instant createdAt = Instant.now();
		
		FriendEntity friend = FriendEntity.builder()
				.withUserId(userId)
				.withFriendId(findFriend.getId())
				.withCreatedAt(Timestamp.from(createdAt))
				.build();
		
		int friendId = friendRepository.saveFriend(friend);
		
		return AddFriendResponse.builder()
				.withMessage(
				"la demande d'ajout est envoyée a " + findFriend.getEmail())
				.build();
				
	}
}
