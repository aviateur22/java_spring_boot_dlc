package com.ctoutweb.dlc.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.model.Friend;

@Repository
public class FriendRepositoryImp implements FriendsRepository {
	private final JdbcTemplate jdbcTemplate;
	

	public FriendRepositoryImp(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int saveFriend(FriendEntity friend) {
		
		return 0;
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
