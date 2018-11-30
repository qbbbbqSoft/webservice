package com.cczu.spider.service;

public interface MailService {
    void sendMail(String type,String text);

    void sendHtmlMail(String type, String html) throws Exception;
}
