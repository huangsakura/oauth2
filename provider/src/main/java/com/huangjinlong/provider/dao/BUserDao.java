package com.huangjinlong.provider.dao;

import com.huangjinlong.provider.bean.entity.BUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BUserDao {

    public BUser loadUserByUsername(@Param("username") String username);
}
