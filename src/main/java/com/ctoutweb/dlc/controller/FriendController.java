package com.ctoutweb.dlc.controller;

import com.ctoutweb.dlc.model.Friend;
import com.ctoutweb.dlc.model.friend.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.service.FriendService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/dlc/friends")
public class FriendController {
	
	private final FriendService friendService;
	private final AnnotationValidator<AddFriendRequest> annotationValidatorAddFriend;
	private final AnnotationValidator<UpdateFriendRelationRequest> annotationValidatorUpdateFriend;
		
	public FriendController(
			FriendService friendService, 
			AnnotationValidator<AddFriendRequest> annotationValidatorAddFriend, 
			AnnotationValidator<UpdateFriendRelationRequest> annotationValidatorUpdateFriend			
			) {
		super();
		this.friendService = friendService;
		this.annotationValidatorAddFriend = annotationValidatorAddFriend;
		this.annotationValidatorUpdateFriend = annotationValidatorUpdateFriend;		
	}

	@PostMapping("")
	public ResponseEntity<AddFriendResponse> addFriend(@RequestBody AddFriendRequest request, @AuthenticationPrincipal UserPrincipal user) {
		annotationValidatorAddFriend.validate(request);
		
		AddFriendResponse response = friendService.addFriend(request, user.getId());
		return new ResponseEntity<AddFriendResponse>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UpdateFriendResponse> acceptFriendRequest(@RequestBody UpdateFriendRelationRequest request, @AuthenticationPrincipal UserPrincipal user){
		annotationValidatorUpdateFriend.validate(request);
		
		UpdateFriendResponse response = friendService.updateFriendRelation(request,user.getId());
		return new ResponseEntity<UpdateFriendResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{friendId}")
	public ResponseEntity<DeleteFriendResponse> deleteFriend(@PathVariable("friendId")
	@NotNull(message = "l'identifiant de l'ami est obligatoire") 
	@Positive(message = "l'identifiant de l'ami est obligatoire") 
	@Min(value = 1, message = "l'identifiant de l'ami est obligatoire") 
	int friendId, @AuthenticationPrincipal UserPrincipal user){
		
		
		DeleteFriendResponse response = friendService.deleteFriend(friendId, user.getId());
		return new ResponseEntity<DeleteFriendResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<FindFriendsByUserIdResponse> findFriendsByUserId(@PathVariable("userId") int userId) {
		FindFriendsByUserIdResponse response = this.friendService.findFriendsByUserId(userId);
		return new ResponseEntity<FindFriendsByUserIdResponse>(response, HttpStatus.OK);
	}

}
