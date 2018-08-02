package com.huangjinlong.client.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huangjinlong.client.bean.constant.GeneralConstant;
import com.huangjinlong.client.service.CallBackService;
import com.huangjinlong.client.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallBackServiceImpl implements CallBackService {
    @Override
    public void authorizeCode(String code, String state) {

        System.out.println("code="+code+";state="+state);
        /**
         * 向认证服务器发送post请求
         */
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("grant_type","authorization_code");
        requestMap.put("code",code);
        //requestMap.put("code","12we44");
        requestMap.put("redirect_uri", GeneralConstant.OAUTH2_CALL_BACK_URL);
        requestMap.put("client_id", GeneralConstant.OAUTH2_CLIENT_ID);
        requestMap.put("client_secret", GeneralConstant.OAUTH2_CLIENT_SECRET);
        try {
            String result = HttpUtil.postMap(GeneralConstant.OAUTH2_GET_ACCESS_TOKEN_URL,requestMap);
            System.out.println("result="+result);
            /**
             * 把string转为 json类
             */
            ObjectMapper mapper = new ObjectMapper();
            //OAuth2TokenResult oAuth2TokenResult = mapper.readValue(result, OAuth2TokenResult.class);
        } catch (IOException e) {

        }
    }

    @Override
    public String clientCredentials() {
        /**
         * 向认证服务器发送post请求
         */
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("grant_type","client_credentials");
        requestMap.put("scope","READ");
        requestMap.put("client_id", GeneralConstant.OAUTH2_CLIENT_ID);
        requestMap.put("client_secret", GeneralConstant.OAUTH2_CLIENT_SECRET);
        try {
            String result = HttpUtil.postMap(GeneralConstant.OAUTH2_GET_ACCESS_TOKEN_URL,requestMap);
            System.out.println("result="+result);
            return result;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public String refreshToken(String refreshToken) {
        /**
         * 向认证服务器发送post请求
         */
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("grant_type","refresh_token");
        requestMap.put("scope","READ");
        requestMap.put("refresh_token",refreshToken);
        requestMap.put("client_id", GeneralConstant.OAUTH2_CLIENT_ID);
        requestMap.put("client_secret", GeneralConstant.OAUTH2_CLIENT_SECRET);
        try {
            return HttpUtil.postMap(GeneralConstant.OAUTH2_GET_ACCESS_TOKEN_URL,requestMap);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
