package com.cczu.spider.repository;

import com.cczu.spider.entity.SysActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SysActivityRepo extends JpaRepository<SysActivityEntity, Long> {
    @Query(value = "select sae from SysActivityEntity sae where sae.ID = ?1")
    SysActivityEntity getOneByID(Long ID);

    @Modifying
    @Transactional
    @Query(value = "update SysActivityEntity sae set sae.activityStatus = ?2 where sae.ID = ?1")
    void setActivityStatusByID(Long ID,Integer status);
}
