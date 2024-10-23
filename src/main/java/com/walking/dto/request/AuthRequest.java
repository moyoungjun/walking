package com.walking.dto.request;

public record AuthRequest(
        String userId,
        String password
) {
}
