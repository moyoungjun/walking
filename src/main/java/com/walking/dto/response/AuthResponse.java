package com.walking.dto.response;

import java.time.LocalDateTime;

public record AuthResponse(
        String tokenType,
        String token,
        LocalDateTime expiresTime
) {
}
