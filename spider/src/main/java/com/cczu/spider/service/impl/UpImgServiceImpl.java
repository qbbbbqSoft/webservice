package com.cczu.spider.service.impl;

import com.cczu.spider.service.UpImgService;
import com.cczu.spider.utils.oss.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UpImgServiceImpl implements UpImgService {
    public static final Logger logger = LoggerFactory.getLogger(UpImgServiceImpl.class);
    @Override
    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public String updateErWeiMa(String path) throws Exception {
        if (path == null || path.equals(null)) {
            throw new Exception("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(path);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }
}
