package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.friend.AddFriendRequest;
import com.ctoutweb.dlc.model.friend.AddFriendResponse;
import com.ctoutweb.dlc.model.friend.DeleteFriendResponse;
import com.ctoutweb.dlc.model.friend.UpdateFriendResponse;
import com.ctoutweb.dlc.model.friend.UpdateFriendRelationRequest;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.service.FriendService;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendController {
	
	private final FriendService friendService;
	
	
	
	public FriendController(FriendService friendService) {
		super();
		this.friendService = friendService;
	}

	@PostMapping("/")
	public ResponseEntity<AddFriendResponse> addFriend(@RequestBody AddFriendRequest request, @AuthenticationPrincipal UserPrincipal user) {
		AddFriendResponse response = friendService.addFriend(request, user.getId());
		return new ResponseEntity<AddFriendResponse>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UpdateFriendResponse> acceptFriendRequest(@RequestBody UpdateFriendRelationRequest request, @AuthenticationPrincipal UserPrincipal user){
		UpdateFriendResponse response = friendService.updateFriendRelation(request,user.getId());
		return new ResponseEntity<UpdateFriendResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{friendId}")
	public ResponseEntity<DeleteFriendResponse> deleteFriend(@PathVariable("friendId") int friendId, @AuthenticationPrincipal UserPrincipal user){
		DeleteFriendResponse response = friendService.deleteFriend(friendId, user.getId());
		return new ResponseEntity<DeleteFriendResponse>(response, HttpStatus.OK);
	}

}
