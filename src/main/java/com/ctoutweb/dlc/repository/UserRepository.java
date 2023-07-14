package com.ctoutweb.dlc.repository;

import java.util.Optional;

import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.model.User;

public interface UserRepository {
	
	int saveUser(RegisterRequest user);
	Optional<User> findUserById(int id);
	Optional<User> findUserByEmail(String email);
}
