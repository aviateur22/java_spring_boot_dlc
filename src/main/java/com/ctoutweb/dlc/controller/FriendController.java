package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.AddFriendRequest;
import com.ctoutweb.dlc.model.AddFriendResponse;
import com.ctoutweb.dlc.model.DeleteFriendResponse;
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
		AddFriendResponse response = friendService.addFriendService(request, user.getId());
		return new ResponseEntity<AddFriendResponse>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<DeleteFriendResponse> deleteFriend(){
		return null;
	}

}
