package com.huangjinlong.oauth2.provider.bean.enums;

public enum ClientStatus {

    WAIT_AUDIT("待审核"),
    AUDITED("已审核");

    private final String message;

    private ClientStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
