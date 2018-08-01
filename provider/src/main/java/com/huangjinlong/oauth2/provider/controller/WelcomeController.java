package com.huangjinlong.oauth2.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    /**
     * 授权码模式
     * http://127.0.0.1:8090/oauth/authorize?response_type=code&client_id=huangjinlong&redirect_uri=http://127.0.0.1:8091/callBack/authorizeCode&scope=READ&state=ionih89endheqenqwe
     *
     *  58030afd-ea58-4d5e-899c-daf3b6e2c314  access
     *  6174eacb-40a2-41a1-9bc0-9ee8c3be02e5  refresh_auth
     *
     *
     * 简化模式
     * http://127.0.0.1:8090/oauth/authorize?response_type=token&client_id=huangjinlong&redirect_uri=http://127.0.0.1:8091/callBack/authorizeToken&scope=READ&state=ionih89endheqenqwe
     *
     * 密码模式 忽略
     *
     * 客户端模式
     * http://127.0.0.1:8091/callBack/clientCredentials
     *
     *
     * 刷新token
     * http://127.0.0.1:8091/callBack/refreshToken?refreshToken=aeee8d2a-710f-470e-9329-74c5f9870ec1
     * @return
     */

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
}
