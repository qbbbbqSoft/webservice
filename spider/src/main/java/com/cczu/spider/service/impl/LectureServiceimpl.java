package com.cczu.spider.service.impl;

import com.cczu.spider.entity.LectureEntity;
import com.cczu.spider.repository.LectureRepo;
import com.cczu.spider.service.LectureService;
import com.cczu.spider.utils.JZSpiderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceimpl implements LectureService {
    @Autowired
    private JZSpiderUtil jzSpiderUtil;

    @Autowired
    private LectureRepo lectureRepo;

    @Override
    public void getAndSaveLectureInfo() {
        try {
            jzSpiderUtil.getAndSaveLectureInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<LectureEntity> getLectureInfo() {

        Sort sort = new Sort(Sort.Direction.ASC, "ordernum");
        Pageable pageable = new PageRequest(0, 100, sort);
        return lectureRepo.findAll(pageable);
    }
}
