package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
		String query = "INSERT INTO friends "
				+ "(user_id, friend_id, is_friend_request_new, is_friend_request_accepted, created_at) "
				+ "VALUES "
				+ "(:userId, :friendId, :isFriendRequestNew, :isFriendRequestAccepted, :createdAt)";
		SqlParameterSource sqlParameter = new BeanPropertySqlParameterSource(friend);
		int insertRow = namedParameterJdbcTemplate.update(query, sqlParameter, this.keyHolder);
		
		if(this.isKeyHolderOrInsertRowUnvalid(insertRow)) throw new InsertSQLException("problème insertion ami");		
		
		return this.getKeyHolderId();
	}

	@Override
	public List<Friend> findFriendsByUserId(int userId) {
		
		String query = "SELECT "
				+ "f.id, f.friend_id, f.nickname, f.is_friend_request_new, f.is_friend_request_accepted, f.is_relation_accepted, "
				+ "f.created_at AS friendCreatedAt, u.email, u.created_at "
				+ "FROM friends AS f "
				+ "JOIN users AS u "
				+ "ON f.friend_id = u.id "
				+ "WHERE f.user_id = ?";
		
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Friend.class), userId);
	}
	
	@Override
	public Optional<Friend> findFriendByUserIdAndFriendId(int userId, int friendId){
		try {
			String query = "SELECT "
					+ "f.id, f.friend_id, f.nickname, f.is_friend_request_new, f.is_friend_request_accepted, f.is_relation_accepted, f.is_relation_deleted, "
					+ "f.created_at AS friendCreatedAt, u.email, u.created_at "
					+ "FROM friends AS f "
					+ "JOIN users AS u "
					+ "ON f.friend_id = u.id "
					+ "WHERE f.user_id = ? AND f.friend_id = ?"
					+ "LIMIT 1";
			Friend friendRelation =  jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Friend.class), userId, friendId);
			
			return Optional.of(friendRelation);
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public int deleteFriendByUserIdAndFriendId(int userId, int friendId, FriendEntity friendOfDeleter) {
		
		String query = "DELETE FROM friends WHERE user_id=? AND friend_id = ?";
		int deleteRow = jdbcTemplate.update(query, userId, friendId);
		
		if(deleteRow == 0) throw new InsertSQLException("problème suppression ami");
		
		// Mise a jour de la relation ami du suppresseur 
		this.updateFriend(friendOfDeleter);
		
		
		
		
		return 0;
	}

	@Override
	public int updateFriend(FriendEntity friend) {
		String query = "UPDATE friends "
				+ "SET "
				+ "is_friend_request_new = :isFriendRequestNew, "
				+ "is_friend_request_accepted = :isFriendRequestAccepted, "
				+ "is_relation_accepted = :isRelationAccepeted, "
				+ "is_relation_deleted = :isRelationDeleted, "
				+ "updated_at = :updatedAt "
				+ "WHERE "
				+ "user_id=:userId AND friend_id=:friendId";
		
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(friend);
		
		int updatedRow = namedParameterJdbcTemplate.update(query, sqlParam);
		
		if(updatedRow == 0) throw new InsertSQLException("problème update ami");
		
		return updatedRow;
		
	}

	@Override
	public List<Friend> findFriendByUserIdWithRelationAccepted(int userId) {
		String query = "SELECT "
				+ "f.id, f.friend_id, f.nickname, f.is_friend_request_new, f.is_friend_request_accepted, f.is_relation_accepted, f.is_relation_deleted, "
				+ "f.created_at AS friendCreatedAt, u.email, u.created_at "
				+ "FROM friends AS f "
				+ "JOIN users AS u "
				+ "ON f.friend_id = u.id "
				+ "WHERE f.user_id = ? "
				+ "AND "
				+ "is_relation_accepted=true";
		
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Friend.class), userId);
	}
}
