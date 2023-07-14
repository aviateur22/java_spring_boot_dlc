package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.model.LoginRequest;
import com.ctoutweb.dlc.model.LoginResponse;
import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
		super();
		this.authService = authService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return new ResponseEntity<LoginResponse>(authService.authenticate(request), HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody RegisterRequest request){
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		int registerId = authService.register(request);
		return new ResponseEntity<Integer>(registerId, HttpStatus.CREATED);
	}

}
