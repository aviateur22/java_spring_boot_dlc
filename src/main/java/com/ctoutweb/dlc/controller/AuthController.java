package com.ctoutweb.dlc.controller;

import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.model.auth.ActivateAccountRequest;
import com.ctoutweb.dlc.model.auth.ActivateAccountResponse;
import com.ctoutweb.dlc.model.auth.CreateAccountRequest;
import com.ctoutweb.dlc.model.auth.CreateAccountResponse;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterEmailRequest;
import com.ctoutweb.dlc.model.auth.RegisterEmailResponse;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.service.AuthService;
@Validated
@RestController
@RequestMapping("/api/v1/dlc/auth")
public class AuthController {
	
	private final AuthService authService;	
	private final AnnotationValidator<RegisterEmailRequest> annotationValidatorRegisterEmail;
	private final AnnotationValidator<CreateAccountRequest> annotationValidtorCreateAccount;
	private final AnnotationValidator<ActivateAccountRequest> annotationValidtorActivateAccount;
	private final AnnotationValidator<LoginRequest> annotationValidtorLogin;
	
	public AuthController(
			AuthService authService,			 
			AnnotationValidator<CreateAccountRequest> annotationValidtorCreateAccount, 
			AnnotationValidator<LoginRequest> annotationValidtorLogin, 
			AnnotationValidator<RegisterEmailRequest> annotationValidatorRegisterEmail,
			AnnotationValidator<ActivateAccountRequest> annotationValidtorActivateAccount
			) {
		//super();
		this.authService = authService;
		this.annotationValidatorRegisterEmail = annotationValidatorRegisterEmail;		
		this.annotationValidtorCreateAccount = annotationValidtorCreateAccount;
		this.annotationValidtorActivateAccount = annotationValidtorActivateAccount;
		this.annotationValidtorLogin = annotationValidtorLogin;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		annotationValidtorLogin.validate(request);
		return new ResponseEntity<LoginResponse>(authService.authenticate(request), HttpStatus.OK);		
	}
	
	@PostMapping("/register-email")
	public ResponseEntity<RegisterEmailResponse> register(@RequestBody RegisterEmailRequest request){
		annotationValidatorRegisterEmail.validate(request);		
	
		RegisterEmailResponse response = authService.registerEmail(request);
		return new ResponseEntity<RegisterEmailResponse>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/create-account")
	public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request){

		annotationValidtorCreateAccount.validate(request);
		
		CreateAccountResponse response = authService.createAccount(request);
		return new ResponseEntity<CreateAccountResponse>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/activate-account")
	public ResponseEntity<ActivateAccountResponse> activateAccount(@RequestBody ActivateAccountRequest request){
		annotationValidtorActivateAccount.validate(request);
		ActivateAccountResponse response = authService.accountActivation(request);
		return new ResponseEntity<ActivateAccountResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/logout/user/{id}")
	public ResponseEntity<LogoutResponse> logout(@PathVariable("id") @Min(value = 1, message = "id utililisateur manquant") int id){
		LogoutResponse response = authService.logout(id);
		return new ResponseEntity<LogoutResponse>(response, HttpStatus.OK);
	}
}
