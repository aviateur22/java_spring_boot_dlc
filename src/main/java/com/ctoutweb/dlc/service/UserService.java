package com.ctoutweb.dlc.service;

import com.ctoutweb.dlc.model.auth.LogoutResponse;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	private final TokenService tokenService;

	public UserService(UserRepository userRepository, TokenService tokenService) {
		super();
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}


	public User findUserInformation(int userId) {
		return userRepository.findUserById(userId).orElseThrow(()->new UserNotFoundException("utilisateur inconnu"));
	}
}
