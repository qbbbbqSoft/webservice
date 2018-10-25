package com.cczu.spider.service;

import com.cczu.spider.entity.LectureEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LectureService {
    void getAndSaveLectureInfo();
    Page<LectureEntity> getLectureInfo();
}
