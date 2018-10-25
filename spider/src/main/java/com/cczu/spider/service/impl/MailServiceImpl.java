package com.cczu.spider.service.impl;

import com.cczu.spider.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Value("${mail.from}")
    private String FROM;

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendMail(String type,String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setTo("cczu_poem@126.com");
        simpleMailMessage.setSubject(type);
        simpleMailMessage.setText(text);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
