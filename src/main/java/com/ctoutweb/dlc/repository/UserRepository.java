package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.UserEntity;
import com.ctoutweb.dlc.model.User;

public interface UserRepository {	
	int saveUser(UserEntity user);
	Optional<User> findUserById(int id);
	Optional<User> findUserByEmail(String email);
	List<User>findAllUsers();
	int deleteByEmail(String email);
}
