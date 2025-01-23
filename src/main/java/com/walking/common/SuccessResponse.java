package com.walking.common;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record SuccessResponse (
    boolean result,
    String msg,
    Object data


){
    public static ResponseEntity<SuccessResponse> toSuccessResponseEntity(Boolean result, Object data) {
        return ResponseEntity
                .status(200)
                .body(
                    SuccessResponse.builder()
                            .result(result)
                            .data(data)
                            .msg("성공")
                            .build()
                );
    }
}
