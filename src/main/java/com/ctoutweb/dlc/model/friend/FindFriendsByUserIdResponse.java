package com.ctoutweb.dlc.model.friend;

import com.ctoutweb.dlc.model.Friend;

import java.util.List;

public class FindFriendsByUserIdResponse  {
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public static final class FindFriendsByUserIdResponseBuilder {
        private List<Friend> friends;

        private FindFriendsByUserIdResponseBuilder() {
        }

        public static FindFriendsByUserIdResponseBuilder aFindFriendsByUserIdResponse() {
            return new FindFriendsByUserIdResponseBuilder();
        }

        public FindFriendsByUserIdResponseBuilder withFriends(List<Friend> friends) {
            this.friends = friends;
            return this;
        }

        public FindFriendsByUserIdResponse build() {
            FindFriendsByUserIdResponse findFriendsByUserIdResponse = new FindFriendsByUserIdResponse();
            findFriendsByUserIdResponse.setFriends(friends);
            return findFriendsByUserIdResponse;
        }
    }
}
