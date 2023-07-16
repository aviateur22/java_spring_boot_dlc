package com.ctoutweb.dlc.service;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.annotation.AnnotationValidator;
import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;	

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	public User findUserInformation(int userId) {
		return userRepository.findUserById(userId).orElseThrow(()->new UserNotFoundException("utilisateur inconnu"));
	}
}
