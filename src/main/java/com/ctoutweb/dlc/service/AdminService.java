package com.ctoutweb.dlc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.repository.UserRepository;

@Service
public class AdminService {
	private final UserRepository userRepository;
	
	
	public AdminService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	public List<User> getAllUsers(){		
		return userRepository.findAllUsers();
	}

}
