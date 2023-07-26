package com.ctoutweb.dlc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterMailingRequest;
import com.ctoutweb.dlc.model.auth.RegisterMailingResponse;
import com.ctoutweb.dlc.model.auth.RegisterRequest;
import com.ctoutweb.dlc.model.auth.RegisterResponse;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;	
	private final AnnotationValidator<RegisterRequest> annotationValidtorRegister;
	private final AnnotationValidator<LoginRequest> annotationValidtorLogin;
	
	public AuthController(
			AuthService authService,			 
			AnnotationValidator<RegisterRequest> annotationValidtorRegister, 
			AnnotationValidator<LoginRequest> annotationValidtorLogin		
			) {
		super();
		this.authService = authService;		
		this.annotationValidtorRegister = annotationValidtorRegister;
		this.annotationValidtorLogin = annotationValidtorLogin;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		annotationValidtorLogin.validate(request);
		return new ResponseEntity<LoginResponse>(authService.authenticate(request), HttpStatus.OK);		
	}
	
	@PostMapping("/register-mailing")
	public ResponseEntity<RegisterMailingResponse> registerMailingLink(@RequestBody RegisterMailingRequest request) {
		RegisterMailingResponse response = authService.registerMailingLink(request);
		return new ResponseEntity<RegisterMailingResponse>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
		annotationValidtorRegister.validate(request);		
	
		RegisterResponse response = authService.register(request);
		return new ResponseEntity<RegisterResponse>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<LogoutResponse> logout(@AuthenticationPrincipal UserPrincipal user){		
		LogoutResponse response = authService.logout(user.getId());
		return new ResponseEntity<LogoutResponse>(response, HttpStatus.OK);
	}

}
