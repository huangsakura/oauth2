package com.huangjinlong.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping(value = "index")
    public String index(@RequestParam("access_token") String access_token) {
        return "恭喜你拿到资源";
    }
}
