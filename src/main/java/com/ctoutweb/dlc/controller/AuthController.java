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
import com.ctoutweb.dlc.model.auth.RegisterEmailRequest;
import com.ctoutweb.dlc.model.auth.RegisterEmailResponse;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;	
	private final AnnotationValidator<RegisterEmailRequest> annotationValidtorRegister;
	private final AnnotationValidator<LoginRequest> annotationValidtorLogin;
	
	public AuthController(
			AuthService authService,			 
			AnnotationValidator<RegisterEmailRequest> annotationValidtorRegister, 
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
	public ResponseEntity<RegisterEmailResponse> register(@RequestBody RegisterEmailRequest request){
		annotationValidtorRegister.validate(request);		
	
		RegisterEmailResponse response = authService.registerEmail(request);
		return new ResponseEntity<RegisterEmailResponse>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<LogoutResponse> logout(@AuthenticationPrincipal UserPrincipal user){		
		LogoutResponse response = authService.logout(user.getId());
		return new ResponseEntity<LogoutResponse>(response, HttpStatus.OK);
	}

}
