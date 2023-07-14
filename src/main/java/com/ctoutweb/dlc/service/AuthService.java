package com.ctoutweb.dlc.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.LoginRequest;
import com.ctoutweb.dlc.model.LoginResponse;
import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;

@Service
public class AuthService {
	
	private UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtIssuer jwtIssuer;

	public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtIssuer jwtIssuer) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.jwtIssuer = jwtIssuer;
	}

	public Integer register(RegisterRequest request) {
		userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
			throw new RuntimeException("utilisateur deja en bdd");
		});;
		
		return userRepository.saveUser(request);
	}
	
	public LoginResponse authenticate(LoginRequest request) {
		
		Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));		
		SecurityContextHolder.getContext().setAuthentication(auth);		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		return new LoginResponse(jwtIssuer.issue(userPrincipal));
	}
	
}
