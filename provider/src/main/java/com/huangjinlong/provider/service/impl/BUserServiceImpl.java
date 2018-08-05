package com.huangjinlong.provider.service.impl;

import com.huangjinlong.provider.bean.constant.GeneralRedisConstant;
import com.huangjinlong.provider.bean.entity.BUser;
import com.huangjinlong.provider.dao.BUserDao;
import com.huangjinlong.provider.service.BUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service(value = "bUserService")
public class BUserServiceImpl implements BUserService, UserDetailsService {

    @Resource
    private BUserDao bUserDao;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        log.info("尝试加载username为{}的用户信息",s);
        String redisKey = GeneralRedisConstant.USER_NAME_REDIS_PREFIX + s;
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (null != o) {
            //log.info("从redis中取出username为{}的用户信息",s);
            //return (BUser)o;
        }

        BUser bUser = bUserDao.loadUserByUsername(s);
        if (bUser == null) {
            throw new UsernameNotFoundException("用户名为"+s+"的用户不存在");
        }
        redisTemplate.opsForValue().set(redisKey,bUser,1L, TimeUnit.MINUTES);
        log.info("从数据库中取出username为{}的用户信息",s);
        return bUser;
    }
}
