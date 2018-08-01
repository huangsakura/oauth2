package com.huangjinlong.oauth2.provider.exception;

public class BusinessException extends RuntimeException {

    private final String code;

    private final String message;

    private static final String DEFAULT_CODE = "SYSTEM";


    public BusinessException(String message) {
        super(message);
        this.message = message;
        this.code = DEFAULT_CODE;
    }

    public BusinessException(String code,String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
