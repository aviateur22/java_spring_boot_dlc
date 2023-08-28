package com.ctoutweb.dlc.repository;

import java.util.List;
import java.util.Optional;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.model.Friend;

public interface FriendsRepository  {
	int saveFriend(FriendEntity friend);
	List<Friend> findFriendsByUserId(int userId);
	Optional<Friend> findFriendByUserIdAndFriendId(int userId, int friendId);
	List<Friend> findFriendByUserIdWithRelationAccepted(int userId);
	int deleteFriendByUserIdAndFriendId(int userId, int friendId, FriendEntity friendOfDeleter);
	int updateFriend(FriendEntity friend);
}