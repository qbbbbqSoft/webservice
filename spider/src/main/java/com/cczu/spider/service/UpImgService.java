package com.cczu.spider.service;

import org.springframework.web.multipart.MultipartFile;


public interface UpImgService {

    String updateHead(MultipartFile file)throws Exception;

    String updateErWeiMa(String path) throws Exception;
}
