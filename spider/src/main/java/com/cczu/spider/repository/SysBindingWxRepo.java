package com.cczu.spider.repository;

import com.cczu.spider.entity.SysBindingWxEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface SysBindingWxRepo extends JpaRepository<SysBindingWxEntity, Long> {

    @Modifying
    @Transactional
    @Query(value="update SysBindingWxEntity sbe set sbe.status = ?1,sbe.updatedate = ?3 where sbe.openid = ?2")
    int update(String status, String openid, Date updatedate);

    @Modifying
    @Transactional
    @Query(value = "delete from SysBindingWxEntity sbe where sbe.openid = ?1")
    void deleteByOpenid(String openid);
}
