package com.ctoutweb.dlc.service;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

//
//	public User findUserInformation(int userId) {
//		return userRepository.findUserById(userId).orElseThrow(()->{
//			throw new RuntimeException("Pas de pp");
//		});
//	}
	
	public <T> Object findUserInformation(T user) {
		String message ="";
	 return user instanceof String ? userRepository.findUserByEmail((String) user).orElseThrow() : userRepository.findUserById((int) user).orElseThrow();
	
	}
}
