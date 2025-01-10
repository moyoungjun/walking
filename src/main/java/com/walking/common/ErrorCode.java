package com.walking.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    //auth
    INVALID_ID_PASSWORD(BAD_REQUEST, "아이디와 비밀번호가 일치하지않습니다."),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    USER_ID_ALREADY_EXISTS(BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    USER_NOT_FOUND(NOT_FOUND, "유저 정보가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
