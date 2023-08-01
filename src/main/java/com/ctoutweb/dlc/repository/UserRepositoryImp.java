package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.RoleUserEntity;
import com.ctoutweb.dlc.entity.UserEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.security.authentication.Role;

@Repository
public class UserRepositoryImp extends IdKeyHolder implements UserRepository{	
	
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	private final RoleUserRepository roleUserRepository;
	private final ProductRepository productRepository;
	private final FriendsRepository friendRepository;
	private final AccountRepository accountRepository;
	private final RandomTextUserRepository randomTextUserRepository;

	public UserRepositoryImp(JdbcTemplate jdbcTemplate, 
			RoleUserRepository roleUserRepository, 
			NamedParameterJdbcTemplate namedParameterJdbcTemplate, 
			ProductRepository productRepository, 
			FriendsRepository friendRepository, 
			AccountRepository accountRepository, 
			RandomTextUserRepository randomTextUserRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.roleUserRepository = roleUserRepository;
		this.productRepository = productRepository;
		this.friendRepository = friendRepository;
		this.accountRepository = accountRepository;
		this.randomTextUserRepository = randomTextUserRepository;
	}

	@Override
	@Transactional
	public int saveUser(UserEntity user) {		
		
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(user);		
		String userQuery = "INSERT INTO users (email) VALUES (:email)";		
		int insertRow = namedParameterJdbcTemplate.update(userQuery, sqlParam, this.keyHolder);		
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("probleme insertion user");
		
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
					.withIsAccountCreated(rs.getBoolean("isAccountCreated"))
					.withMaximumAccountCreationDate(rs.getTimestamp("maximum_account_creation_date"))
					.build(), userId);
			
			findUser.setAccount(accountRepository.findAccountByUserId(userId).orElse(null));
			findUser.setRoles(roleUserRepository.findUserRoleByUserId(userId));
			findUser.setFriends(friendRepository.findFriendsByUserId(userId));
			findUser.setProducts(productRepository.findProductsByUserId(userId));
			findUser.setRandomTexts(randomTextUserRepository.findByUserI(userId));
			
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
					.withIsAccountCreated(rs.getBoolean("is_account_created"))
					.withMaximumAccountCreationDate(rs.getTimestamp("maximum_account_creation_date"))
					.build(),
					email);			
			findUser.setAccount(accountRepository.findAccountByUserId(findUser.getId()).orElse(null));
			findUser.setRoles(roleUserRepository.findUserRoleByUserId(findUser.getId()));
			findUser.setRandomTexts(randomTextUserRepository.findByUserI(findUser.getId()));
			
			return Optional.of(findUser);
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<User> findAllUsers() {
		String userQuery = "SELECT * FROM users";			
		List<User> findUsers = jdbcTemplate.query(userQuery, 
				(rs, rowNum)->User.builder()
				.withId(rs.getInt("id"))
				.withEmail(rs.getString("email"))
				.withIsAccountCreated(rs.getBoolean("is_account_created"))
				.build())
		.stream()
		.map(user->User.builder()
				.withId(user.getId())
				.withEmail(user.getEmail())
				.withAccount(accountRepository.findAccountById(user.getId()).orElse(null))
				.withRoles(roleUserRepository.findUserRoleByUserId(user.getId()))
				.withFriends(friendRepository.findFriendsByUserId(user.getId()))
				.withProducts(productRepository.findProductsByUserId(user.getId()))
				.build())
		.collect(Collectors.toList());
		
		
		
		return findUsers;
	}
	
	public int deleteByEmail(String email) {
		String query = "DELETE FROM users WHERE email = ?";
		int deleteRow = jdbcTemplate.update(query, email);
		
		if(deleteRow == 0) throw new InsertSQLException("erreur suppression randomText");		
		return deleteRow;
	}
}
