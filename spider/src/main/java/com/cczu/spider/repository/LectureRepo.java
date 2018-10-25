package com.cczu.spider.repository;

import com.cczu.spider.entity.LectureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepo extends JpaRepository<LectureEntity,Long> {

//    @Query("select tl from tool_lecture tl ORDER BY tl.ordernum ASC")
//    Page<LectureEntity> getInfoOrderBOrderNum();

}
