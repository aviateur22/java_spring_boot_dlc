package com.ctoutweb.dlc.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.exception.custom.InsertSQLException;
import com.ctoutweb.dlc.model.Friend;

@Repository
public class FriendRepositoryImp extends IdKeyHolder implements FriendsRepository {
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public FriendRepositoryImp(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	@Transactional
	public int saveFriend(FriendEntity friend) {		
		String query = "INSERT INTO friends (user_id, friend_id, created_at) VALUES (?, ?, ?)";
		SqlParameterSource sqlParameter = new BeanPropertySqlParameterSource(friend);
		int insertRow = namedParameterJdbcTemplate.update(query, sqlParameter, this.keyHolder);
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("problème insertion ami");		
		
		return this.getKeyHolderId();
	}

	@Override
	public List<Friend> findFriendByUserId(int userId) {
		
		String query = "SELECT "
				+ "f.id, f.friend_id, f.nickname, f.is_friend_request_view, f.is_friend_request_accepted, f.is_relation_accepted, "
				+ "f.created_at AS friendCreatedAt, u.email, u.created_at "
				+ "FROM friends AS f "
				+ "JOIN users AS u "
				+ "ON f.friend_id = u.id "
				+ "WHERE f.user_id = ?";
		
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Friend.class), userId);
	}

	@Override
	public int deleteFriendByUserAndFriendId(int userId, int friendId) {
		
		return 0;
	}

	@Override
	public void acceptFriend(int userId, int friendId) {
		
		
	}

	@Override
	public void refuseFriend(int userId, int friendId) {
		
		
	}

}
