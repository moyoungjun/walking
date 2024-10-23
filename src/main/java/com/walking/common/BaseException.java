package com.walking.common;

import java.io.Serial;

public class BaseException extends AbstractBaseException {
    @Serial
    private static final long serialVersionUID = 2422188521343183500L;

    public BaseException(ErrorCode errCode) {
        this.errCode = errCode;
    }

}
