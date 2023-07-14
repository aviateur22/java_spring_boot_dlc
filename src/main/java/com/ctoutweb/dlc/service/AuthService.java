package com.ctoutweb.dlc.service;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class AuthService {
	private UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Integer register(RegisterRequest request) {
		userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
			throw new RuntimeException("utilisateur deja en bdd");
		});;
		
		return userRepository.saveUser(request);
		
	}
}
