package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

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
        simpleMailMessage.setText("<html>" +
                "<p>我HTML哦</p>" +
                "</html>");
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMail(SysActivityEntity sysActivityEntity, List<SysSignUpEntity> sysSignUpEntities, int type, String recieveEmail) throws Exception{
        String html = "";
        if (type == 1) {
            html="<html><head>\n" +
                    "<base target=\"_blank\">\n" +
                    "<style type=\"text/css\">\n" +
                    "::-webkit-scrollbar{ display: none; }\n" +
                    "</style>\n" +
                    "<style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
                    "#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}\n" +
                    "</style>\n" +
                    "\n" +
                    "</head>\n" +
                    "<body tabindex=\"0\" role=\"listitem\">\n" +
                    "        <style>\n" +
                    "            html, body {\n" +
                    "                height: 100%;\n" +
                    "            }\n" +
                    "\n" +
                    "            body {\n" +
                    "                margin: 0;\n" +
                    "                padding: 0;\n" +
                    "                width: 100%;\n" +
                    "                display: table;\n" +
                    "                font-weight: 100;\n" +
                    "                font-family: 'Lato';\n" +
                    "            }\n" +
                    "\n" +
                    "            .container {\n" +
                    "                text-align: center;\n" +
                    "                display: table-cell;\n" +
                    "                vertical-align: middle;\n" +
                    "            }\n" +
                    "\n" +
                    "            .content {\n" +
                    "                text-align: center;\n" +
                    "                display: inline-block;\n" +
                    "            }\n" +
                    "\n" +
                    "            .title {\n" +
                    "                font-size: 32px;\n" +
                    "            }\n" +
                    "\t\t\t\n" +
                    "\n" +
                    "        </style>\n" +
                    "    \n" +
                    "    \n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"content\">\n" +
                    "                <div class=\"title\">" + sysActivityEntity.getActivityName() + "</div>\n" +
                    "\t\t\t\t<div class=\"visible-print text-center\">\n" +
                    "\t\t\t\n" +
                    "\t\t\t\t\t<img src=\"" + sysActivityEntity.getActivityQrCodeUrl() +"\" style=\"padding:20px;\">  \n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t\t<table width=\"100%\">\n" +
                    "\t\t\t\t<tbody>\n" +
                    "\t\t\t\t<tr>\n" +
                    "\t\t\t\t<td>&nbsp;</td>\n" +
                    "\t\t\t\t<td align=\"center\" width=\"400px\">\n" +
                    "\t\t\t\t<table>\n" +
                    "\t\t\t\t<tbody><tr><td style=\"text-align:left\">\n" +
                    "\t\t\t\t扫码签到步骤：<br>\n" +
                    "\t\t\t\t1. 从微信小程序中搜索“常州大学课表”；<br>\n" +
                    "\t\t\t\t2. 打开“常州大学课表”，点击“扫码签到”，扫码。<br>\n" +
                    "\t\t\t\t</td></tr></tbody>\n" +
                    "\t\t\t\t</table>\n" +
                    "\t\t\t\t</td>\n" +
                    "\t\t\t\t<td>&nbsp;</td></tr>\n" +
                    "\t\t\t\t</tbody></table>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    \n" +
                    "\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "<style type=\"text/css\">\n" +
                    "body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                    "td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                    "pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                    "th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                    "img{ border:0}\n" +
                    "header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                    "blockquote{margin-right:0px}\n" +
                    "</style>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<style id=\"ntes_link_color\" type=\"text/css\">a,td a{color:#138144}</style>\n" +
                    "\n" +
                    "\n" +
                    "<script type=\"text/javascript\">\n" +
                    "window.r_a_h=function(){return [{'id':'2','filename':'_','inlined':true}];};\n" +
                    "</script>\n" +
                    "\n" +
                    "<div id=\"ntes_read_img_menu_0\" style=\"position: absolute; left: 684px; top: 33px; display: none;\"><div style=\"display:inline-block;+display:inline;+zoom:1;font-size: 12px; font-weight: normal;line-height: normal;margin-right:6px;\"><a href=\"javascript:void(0);\" hidefocus=\"hidefocus\" title=\"将图片保存到网盘\" onclick=\"NTES_Events.saveToNF('2');return false;\" style=\"vertical-align:bottom;display:inline-block;+display:inline;+zoom:1;position:relative;+zoom:1;background-image:url(https://mimg.127.net/ggimg/all/img18/140308_nui_btn.png);background-position:0 ; ;border:1px solid #999;border-top-left-radius:3px;border-bottom-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;text-decoration:none;color:#555;height:26px;width:38px;line-height:0;font-size:0;\"><img src=\"https://mimg.127.net/ggimg/all/img18/140312_wp_2x.png\" style=\"position:absolute;left:10px;top:6px;border:0;\" width=\"16\" height=\"13\"></a></div></div></body></html>";
        } else if (type == 2){
            String list = "";
            for (SysSignUpEntity entity:sysSignUpEntities) {
                list = list + "<tr>\n" +
                        "                                        <td class=\"tdimg\"> <img width=\"32\" height=\"32\" class=\"iconimg\" src=\"" + entity.getWxheadimageurl() + "\"></td>\n" +
                        "                                        <td>" + entity.getWxusername() + " </td>\n" +
                        "                                        <td>" + entity.getPhone() +"</td>\n" +
                        "                                        <td> " + entity.getSigndate() + "</td>\n" +
                        "                                    </tr>";
            }
            html = "<html>\n" +
                    "<head>\n" +
                    "    <base target=\"_blank\">\n" +
                    "    <style type=\"text/css\">\n" +
                    "    ::-webkit-scrollbar {\n" +
                    "        display: none;\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "    <style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
                    "    #divNeteaseBigAttach,\n" +
                    "    #divNeteaseBigAttach_bak {\n" +
                    "        display: none;\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body tabindex=\"0\" role=\"listitem\">\n" +
                    "    <style>\n" +
                    "    html,\n" +
                    "    body {\n" +
                    "        height: 100%;\n" +
                    "    }\n" +
                    "    body {\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "        width: 100%;\n" +
                    "        display: table;\n" +
                    "        font-weight: 100;\n" +
                    "        font-family: 'Lato';\n" +
                    "    }\n" +
                    "    .container {\n" +
                    "        display: table-cell;\n" +
                    "        vertical-align: middle;\n" +
                    "    }\n" +
                    "    .content {\n" +
                    "        display: inline-block;\n" +
                    "    }\n" +
                    "    .title {\n" +
                    "        font-size: 18px;\n" +
                    "        padding: 10px 0;\n" +
                    "    }\n" +
                    "    .tHeader {\n" +
                    "        margin-top: 15px;\n" +
                    "        border-collapse: collapse;\n" +
                    "        border: 1px solid #aaa;\n" +
                    "        font-size: 16px;\n" +
                    "    }\n" +
                    "    table.tHeader th {\n" +
                    "        padding: 0;\n" +
                    "        height: 32px;\n" +
                    "        vertical-align: middle;\n" +
                    "        text-align: left;\n" +
                    "        background-color: #F5F5F5;\n" +
                    "        border: 1px solid #ddd;\n" +
                    "        font-size: 16px;\n" +
                    "    }\n" +
                    "    tr {\n" +
                    "        display: table-row;\n" +
                    "        vertical-align: inherit;\n" +
                    "        border: 1px solid #ddd;\n" +
                    "    }\n" +
                    "    table.tHeader td {\n" +
                    "        padding: 0;\n" +
                    "        height: 36px;\n" +
                    "        width: 48px;\n" +
                    "        vertical-align: middle;\n" +
                    "        background: #fdfcf8 no-repeat center;\n" +
                    "        border: 0;\n" +
                    "        text-align: left;\n" +
                    "        font-size: 14px;\n" +
                    "    }\n" +
                    "    .iconimg {\n" +
                    "        width: 32px;\n" +
                    "        height: 32px;\n" +
                    "        border-radius: 50%;\n" +
                    "        margin: 2px 2px 2px 5px;\n" +
                    "    }\n" +
                    "    table td img {\n" +
                    "        width: 32px;\n" +
                    "        height: 32px;\n" +
                    "        border-radius: 50%;\n" +
                    "        margin: 2px 2px 2px 5px;\n" +
                    "    }\n" +
                    "    .tdimg {\n" +
                    "        text-align: center;\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"content\">\n" +
                    "            <div class=\"title\">签到详情：" + sysActivityEntity.getActivityName() +"</div>\n" +
                    "            <table width=\"600px\">\n" +
                    "                <tbody>\n" +
                    "                    <tr>\n" +
                    "                        <td align=\"left\" style=\"height:60px;margin:15px;color:#333333;text-align:left;\">\n" +
                    "                            <div style=\"text-align:left;font-size:14px;display:inline-block\">签到人数 " + sysSignUpEntities.size() +"</div>\n" +
                    "                        </td>\n" +
                    "                        <td>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>\n" +
                    "                            <table class=\"tHeader\">\n" +
                    "                                <tbody>\n" +
                    "                                    <tr>\n" +
                    "                                        <th style=\"width:30px;border:0\"></th>\n" +
                    "                                        <th style=\"width:150px;border:0;padding-left:5px\">姓名</th>\n" +
                    "                                        <th style=\"width:150px;border:0\"> 手机号</th>\n" +
                    "                                        <th style=\"width:200px;border:0\">签到时间</th>\n" +
                    "                                    </tr>\n" +
                    list +
                    "                                </tbody>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                </tbody>\n" +
                    "                <tbody></tbody>\n" +
                    "            </table>\n" +
                    "            <table>\n" +
                    "            </table>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <style type=\"text/css\">\n" +
                    "    body {\n" +
                    "        font-size: 14px;\n" +
                    "        font-family: arial, verdana, sans-serif;\n" +
                    "        line-height: 1.666;\n" +
                    "        padding: 0;\n" +
                    "        margin: 0;\n" +
                    "        overflow: auto;\n" +
                    "        white-space: normal;\n" +
                    "        word-wrap: break-word;\n" +
                    "        min-height: 100px\n" +
                    "    }\n" +
                    "    td,input,button,select,body {\n" +
                    "        font-family: Helvetica, 'Microsoft Yahei', verdana\n" +
                    "    }\n" +
                    "    pre {\n" +
                    "        white-space: pre-wrap;\n" +
                    "        white-space: -moz-pre-wrap;\n" +
                    "        white-space: -pre-wrap;\n" +
                    "        white-space: -o-pre-wrap;\n" +
                    "        word-wrap: break-word;\n" +
                    "        width: 95%\n" +
                    "    }\n" +
                    "    th,td {\n" +
                    "        font-family: arial, verdana, sans-serif;\n" +
                    "        line-height: 1.666\n" +
                    "    }\n" +
                    "    img {\n" +
                    "        border: 0\n" +
                    "    }\n" +
                    "    header,footer,section,aside,article,nav,hgroup,figure,figcaption {\n" +
                    "        display: block\n" +
                    "    }\n" +
                    "\n" +
                    "    blockquote {\n" +
                    "        margin-right: 0px\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "    <style id=\"ntes_link_color\" type=\"text/css\">\n" +
                    "    a,\n" +
                    "    td a {\n" +
                    "        color: #138144\n" +
                    "    }\n" +
                    "    </style>\n" +
                    "</body>\n" +
                    "</html>";
        } else {
            html = "<html><h1>邮件发送发生错误<h1></html>";
        }
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
        //SimpleMailMessage mailMsg = new SimpleMailMessage();//只支持文字
        helper.setFrom(FROM);//发件人
        helper.setTo(recieveEmail);//收件人
        helper.setSubject("常州大学课表小工具-扫码签到");//邮件标题
        helper.setText(html,true); //测试内容（html）
        try {
            javaMailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
