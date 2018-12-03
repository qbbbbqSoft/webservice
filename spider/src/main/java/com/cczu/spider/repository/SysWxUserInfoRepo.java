package com.cczu.spider.repository;

import com.cczu.spider.entity.SysWxUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysWxUserInfoRepo extends JpaRepository<SysWxUserInfoEntity, Long> {

    @Query(value = "select swuie from SysWxUserInfoEntity swuie where swuie.openid = ?1")
    SysWxUserInfoEntity getOneWxUserInfoByOpenid(String openid);
}
