package com.huangjinlong.oauth2.provider.dao;

import com.huangjinlong.oauth2.provider.bean.entity.PClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PClientDao {

    public PClient loadClientByClientId(@Param("clientId") String clientId);
}
