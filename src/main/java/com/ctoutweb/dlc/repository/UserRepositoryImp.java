package com.ctoutweb.dlc.repository;

import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.RoleUserEntity;
import com.ctoutweb.dlc.model.RegisterRequest;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.security.Role;

@Repository
public class UserRepositoryImp extends IdKeyHolder implements UserRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	private final RoleUserRepository roleUserRepository;
	

	public UserRepositoryImp(JdbcTemplate jdbcTemplate, RoleUserRepository roleUserRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.roleUserRepository = roleUserRepository;
	}

	@Override
	@Transactional
	public int saveUser(RegisterRequest user) {		
		
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(user);		
		String userQuery = "INSERT INTO users (email, password) VALUES (:email, :password)";		
		int insertRow = namedParameterJdbcTemplate.update(userQuery, sqlParam, this.keyHolder);
		System.out.println(this.getKeyHolderId());
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new RuntimeException("probleme insertion user");
		
		RoleUserEntity addUserRole = RoleUserEntity.builder()
				.withRoleId(Role.ADMIN.getValue())
				.withUserId(this.getKeyHolderId())
				.build();
		
		roleUserRepository.saveRoleUser(addUserRole);
		
		return this.getKeyHolderId();
	}

	@Override
	public Optional<User> findUserById(int id) {
		try {
			String query = "SELECT * FROM users WHERE id=? LIMIT 1";
			User findUser = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), id);
			return Optional.of(findUser);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		try {
			String query = "SELECT * FROM users WHERE email=? LIMIT 1";
			User findUser = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), email);
			return Optional.of(findUser);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}
}
