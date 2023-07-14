package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") int id) {
		User user = (User) userService.findUserInformation(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
