package com.cczu.spider.repository;

import com.cczu.spider.entity.SysWxUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysWxUserInfoRepo extends JpaRepository<SysWxUserInfoEntity, Long> {

    @Query( nativeQuery=true, value = "SELECT * FROM sys_wxuserinfo WHERE openID = ?1 ORDER BY createDate DESC LIMIT 1")
    SysWxUserInfoEntity getOneWxUserInfoByOpenid(String openid);
}
