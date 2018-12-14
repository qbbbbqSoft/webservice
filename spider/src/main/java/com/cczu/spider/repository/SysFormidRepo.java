package com.cczu.spider.repository;

import com.cczu.spider.entity.SysFormidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysFormidRepo extends JpaRepository<SysFormidEntity, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM sys_formid WHERE ID = (SELECT MIN(ID) FROM sys_formid WHERE openID = ?1 AND isUsed = FALSE and `timestamp` > ?2)")
    SysFormidEntity getNeedRemindUserFormid(String openid, long timeStamp);
}
