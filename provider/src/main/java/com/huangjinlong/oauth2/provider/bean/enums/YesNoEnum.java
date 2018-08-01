package com.huangjinlong.oauth2.provider.bean.enums;

public enum YesNoEnum {

    YES("是"),
    NO("否");

    private final String message;

    private YesNoEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
