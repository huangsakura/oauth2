package com.huangjinlong.oauth2.client.controller;

import com.huangjinlong.oauth2.client.service.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/callBack")
public class CallBackController {

    @Autowired
    private CallBackService callBackService;

    /**
     * 授权码模式 获取code的回调入口
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/authorizeCode",method = RequestMethod.GET)
    public String authorizeCode(@RequestParam(value = "code") String code,
                            @RequestParam(value = "state",required = false) String state) {
        callBackService.authorizeCode(code,state);
        return "authorizeCode";
    }

    /**
     * 简化模式回调入口
     * @return
     */
    @RequestMapping(value = "/authorizeToken",method = RequestMethod.GET)
    public String authorizeToken() {
        return "authorizeToken";
    }


    /**
     * 用于测试，主动发起客户端模式的验证
     */
    @RequestMapping(value = "/clientCredentials",method = RequestMethod.GET)
    @ResponseBody
    public String clientCredentials() {
        return callBackService.clientCredentials();
    }


    /**
     * 用于测试，主动发起刷新token
     */
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    @ResponseBody
    public String refreshToken(String refreshToken) {
        return callBackService.refreshToken(refreshToken);
    }
}
