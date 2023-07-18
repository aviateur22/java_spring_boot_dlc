package com.ctoutweb.dlc.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutRequest;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterRequest;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;

@Service
public class AuthService {	
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final JwtIssuer jwtIssuer;

	public AuthService(
			UserRepository userRepository, 
			AuthenticationManager authenticationManager, 
			JwtIssuer jwtIssuer, 
			TokenService tokenService) {
		super();		
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
		this.jwtIssuer = jwtIssuer;
	}

	public Integer register(RegisterRequest request) {			
		userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
			throw new UserFindException("cet email est déja utilisé");
		});
		
		return userRepository.saveUser(request);
	}
	
	public LoginResponse authenticate(LoginRequest request) {		
		Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));		
		SecurityContextHolder.getContext().setAuthentication(auth);		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		// gestion du token a jouter en bdd
		TokenIssue tokenIssue = jwtIssuer.issue(userPrincipal);		
		tokenService.saveToken(userPrincipal.getId(), tokenIssue);
		
		return new LoginResponse(tokenIssue.getToken());
	}
	
	public LogoutResponse logout(LogoutRequest request) {
		return null;	
	}
	
	
	
}
