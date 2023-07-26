package com.ctoutweb.dlc.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterRequest;
import com.ctoutweb.dlc.model.auth.RegisterResponse;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;

@Service
public class AuthService {	
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final JwtIssuer jwtIssuer;

	public AuthService(
			UserRepository userRepository, 
			AuthenticationManager authenticationManager, 
			JwtIssuer jwtIssuer, 
			TokenService tokenService, 
			PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;		
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
		this.jwtIssuer = jwtIssuer;
	}

	public RegisterResponse register(RegisterRequest request) {			
		userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
			throw new UserFindException("cet email est déja utilisé");
		});
		
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		int userId = userRepository.saveUser(request);
		
		RegisterResponse response = RegisterResponse.builder()
		.withMessage("votre inscription est confirmée")
		.withUserId(userId)
		.build();
		
		return response;
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
	
	public LogoutResponse logout(int userId) {
		tokenService.deleteToken(userId);
		return LogoutResponse.builder().withMessage("à bientôt").build();
	}
	
	
	
}
