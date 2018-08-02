package com.huangjinlong.provider.service.impl;

import com.huangjinlong.provider.bean.constant.GeneralRedisConstant;
import com.huangjinlong.provider.bean.entity.PClient;
import com.huangjinlong.provider.dao.PClientDao;
import com.huangjinlong.provider.exception.BusinessException;
import com.huangjinlong.provider.service.PClientService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 按照标准的spring security + oauth2 的要求，本来该使用
 *  org.springframework.security.oauth2.provider.client.JdbcClientDetailsService的。
 *  但是那种表结构是死的，不方便我们自己拓展表结构。
 *  所以干脆自定义client的表结构，并自己写service。
 *
 *  只要保证 实体类实现了 org.springframework.security.oauth2.provider.ClientDetails接口，
 *          service 实现了 org.springframework.security.oauth2.provider.ClientDetailsService 接口
 *
 *  就行了。
 *
 *  引入redis，保证访问量大的时候，数据库不崩。
 */
@Service(value = "pClientService")
public class PClientServiceImpl implements PClientService, ClientDetailsService {

    @Resource
    private PClientDao pClientDao;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        String redisKey = GeneralRedisConstant.P_CLIENT_REDIS_PREFIX + clientId;

        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o != null) {
            // TODO
            //return (PClient)o;
        }
        PClient pClient = pClientDao.loadClientByClientId(clientId);
        if (null == pClient) {
            throw new BusinessException("CLIENT_ID_NOT_EXIST","客户id不存在");
        }
        redisTemplate.opsForValue().set(redisKey,pClient,30L, TimeUnit.DAYS);
        return pClient;
    }
}
