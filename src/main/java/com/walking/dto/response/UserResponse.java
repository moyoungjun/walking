package com.walking.dto.response;

import com.walking.entity.User;

public record UserResponse(String userId, String userName) {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getUserId(), user.getUsername());
    }
}