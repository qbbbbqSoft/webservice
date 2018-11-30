package com.cczu.spider.repository;

import com.cczu.spider.entity.SysActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysActivityRepo extends JpaRepository<SysActivityEntity, Long> {
    @Query(value = "select sae from SysActivityEntity sae where sae.ID = ?1")
    SysActivityEntity getOneByID(Long ID);
}
