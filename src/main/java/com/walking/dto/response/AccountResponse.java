package com.walking.dto.response;

import java.util.Set;


public record AccountResponse(
        Long id,
        String userId,
        String userName,
        Set<String> authorities
) {

}
