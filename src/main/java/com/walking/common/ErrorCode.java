package com.walking.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    //400 - auth
    INVALID_ID_PASSWORD(BAD_REQUEST, "아이디와 비밀번호가 일치하지않습니다."),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
