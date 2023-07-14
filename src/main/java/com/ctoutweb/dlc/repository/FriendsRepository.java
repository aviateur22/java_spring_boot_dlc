package com.ctoutweb.dlc.repository;

import java.util.List;

import com.ctoutweb.dlc.entity.FriendEntity;
import com.ctoutweb.dlc.model.Friend;

public interface FriendsRepository  {
	int saveFriend(FriendEntity friend);
	List<Friend> findFriendByUserId(int userId);
	int deleteFriendByUserAndFriendId(int userId, int friendId);
	void acceptFriend(int userId, int friendId);
	void refuseFriend(int userId, int friendId);
}