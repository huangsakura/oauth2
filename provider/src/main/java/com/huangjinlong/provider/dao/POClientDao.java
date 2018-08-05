package com.huangjinlong.provider.dao;

import com.huangjinlong.provider.bean.entity.POClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface POClientDao {

    public POClient loadClientByClientId(@Param("clientId") String clientId);
}
