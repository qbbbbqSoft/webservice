package com.cczu.spider.repository;

import com.cczu.spider.entity.SysZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface SysZoneRepo extends JpaRepository<SysZoneEntity,Long> {


}
