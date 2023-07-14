package com.ctoutweb.dlc.repository;

import java.util.List;

import com.ctoutweb.dlc.entity.RoleUserEntity;
import com.ctoutweb.dlc.model.UserRole;

public interface RoleUserRepository {
	int saveRoleUser(RoleUserEntity roleUser);
	List<UserRole> findUserRoleByUserId(int userId);

}
