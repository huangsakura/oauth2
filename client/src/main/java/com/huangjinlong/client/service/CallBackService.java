package com.huangjinlong.client.service;

public interface CallBackService {

    public void authorizeCode(String code,String state);

    public String clientCredentials();

    public String refreshToken(String refreshToken);
}
