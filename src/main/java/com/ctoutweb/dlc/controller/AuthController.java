package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	
	
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@GetMapping("/login")
	public ResponseEntity<String> login() {
		return null;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody RegisterRequest request){
		int registerId = authService.register(request);
		return new ResponseEntity<Integer>(registerId, HttpStatus.CREATED);
	}

}
