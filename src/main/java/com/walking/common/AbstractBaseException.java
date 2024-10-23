package com.walking.common;

import java.io.Serial;

public abstract class AbstractBaseException extends RuntimeException {
    //객체 직렬화
    @Serial
    private static final long serialVersionUID = -3929818811922272041L;

    protected ErrorCode errCode;

    public AbstractBaseException() {

    }

    public ErrorCode getResponseCode() {
        return errCode;
    }

}
