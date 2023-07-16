package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.service.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;
	private final AnnotationValidator<Integer> annotationValidator;
	
	public UserController(UserService userService, AnnotationValidator<Integer> annotationValidator) {
		super();
		this.userService = userService;
		this.annotationValidator = annotationValidator;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") @Min(value = 1, message = "oupss") int id) {
		annotationValidator.validate(id);
		User user = (User) userService.findUserInformation(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
