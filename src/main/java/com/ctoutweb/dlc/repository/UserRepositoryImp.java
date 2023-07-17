package com.ctoutweb.dlc.repository;

import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.RoleUserEntity;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.auth.RegisterRequest;
import com.ctoutweb.dlc.security.Role;

@Repository
public class UserRepositoryImp extends IdKeyHolder implements UserRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	private final RoleUserRepository roleUserRepository;
	private final ProductRepository productRepository;
	private final FriendsRepository friendRepository;
	

	public UserRepositoryImp(JdbcTemplate jdbcTemplate, 
			RoleUserRepository roleUserRepository, 
			NamedParameterJdbcTemplate namedParameterJdbcTemplate, 
			ProductRepository productRepository, 
			FriendsRepository friendRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.roleUserRepository = roleUserRepository;
		this.productRepository = productRepository;
		this.friendRepository = friendRepository;
	}

	@Override
	@Transactional
	public int saveUser(RegisterRequest user) {		
		
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(user);		
		String userQuery = "INSERT INTO users (email, password) VALUES (:email, :password)";		
		int insertRow = namedParameterJdbcTemplate.update(userQuery, sqlParam, this.keyHolder);		
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new RuntimeException("probleme insertion user");
		
		RoleUserEntity addUserRole = RoleUserEntity.builder()
				.withRoleId(Role.ADMIN.getValue())
				.withUserId(this.getKeyHolderId())
				.build();
		
		roleUserRepository.saveRoleUser(addUserRole);
		
		return this.getKeyHolderId();
	}

	@SuppressWarnings("null")
	@Override	
	public Optional<User> findUserById(int userId) {
		try {
			String userQuery = "SELECT * FROM users WHERE id=? LIMIT 1";			
			User findUser = jdbcTemplate.queryForObject(userQuery, 
					(rs, rowNum)->User.builder()
					.withId(rs.getInt("id"))
					.withEmail(rs.getString("email"))
					.withPassword(rs.getString("password"))
					.withLastLoginAt(rs.getTimestamp("last_login_at"))
					.withCreatedAt(rs.getTimestamp("created_at"))
					.withUpdatedAt(rs.getTimestamp("updated_at"))
					.withIsAccountActive(rs.getBoolean("is_account_active"))
					.build(), userId);
			
			findUser.setRoles(roleUserRepository.findUserRoleByUserId(userId));
			findUser.setFriends(friendRepository.findFriendsByUserId(userId));
			findUser.setProducts(productRepository.findProductsByUserId(userId));
			
			return Optional.of(findUser);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@SuppressWarnings("null")
	@Override
	public Optional<User> findUserByEmail(String email) {
		try {
			String query = "SELECT * FROM users WHERE email=? LIMIT 1";
			// User findUser = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), email);
			User findUser = jdbcTemplate.queryForObject(query, 
					(rs, rowNum)-> User.builder()
					.withId(rs.getInt("id"))
					.withEmail(rs.getString("email"))
					.withPassword(rs.getString("password"))
					.withLastLoginAt(rs.getTimestamp("last_login_at"))
					.withCreatedAt(rs.getTimestamp("created_at"))
					.withUpdatedAt(rs.getTimestamp("updated_at"))
					.withIsAccountActive(rs.getBoolean("is_account_active"))
					.build(),
					email);
			
			
			findUser.setRoles(roleUserRepository.findUserRoleByUserId(findUser.getId()));
			System.out.println(findUser);
			return Optional.of(findUser);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}
}
