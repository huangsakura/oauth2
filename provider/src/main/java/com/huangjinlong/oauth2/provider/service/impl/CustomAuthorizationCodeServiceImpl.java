package com.huangjinlong.oauth2.provider.service.impl;

import com.huangjinlong.oauth2.provider.bean.constant.GeneralRedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 默认方式是 把生成的授权码放入到内存里面，参见 org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices类
 *
 * 但是这样不方便我们管理授权码，故继承相关类并重写相关方法，让我们方便控制 授权码
 */
@Service(value = "customAuthorizationCodeService")
public class CustomAuthorizationCodeServiceImpl extends RandomValueAuthorizationCodeServices {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    protected void store(String s, OAuth2Authentication oAuth2Authentication) {
        /**
         * 把生成的授权码放入redis，十分钟
         */
        System.out.println("AuthorizationCode="+s);
        redisTemplate.opsForValue().set(
                GeneralRedisConstant.AUTH_CODE_REDIS_PREFIX + s,oAuth2Authentication,10L, TimeUnit.MINUTES);
    }

    @Override
    protected OAuth2Authentication remove(String s) {
        String key = GeneralRedisConstant.AUTH_CODE_REDIS_PREFIX + s;
        Object o = redisTemplate.opsForValue().get(key);
        if (null == o) {
            return null;
        }
        redisTemplate.delete(key);
        return (OAuth2Authentication)o;
    }
}
