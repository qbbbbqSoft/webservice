package com.cczu.spider.repository;

import com.cczu.spider.entity.SysIndexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysIndexRepo extends JpaRepository<SysIndexEntity, Long> {

    @Query(value = "select sid from SysIndexEntity sid where sid.position = ?1")
    List<SysIndexEntity> getIndexByPosition(String position);
}
