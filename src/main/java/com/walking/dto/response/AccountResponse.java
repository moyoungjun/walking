package com.walking.dto.response;

import java.util.Set;


public record AccountResponse(
        Long userSeq,
        String userId,
        String userName,
        Set<String> authorities
) {

}
