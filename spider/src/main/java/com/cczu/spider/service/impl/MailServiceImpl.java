package com.cczu.spider.service.impl;

import com.cczu.spider.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

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
    public void sendHtmlMail(String type, String html) throws Exception{
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
                "                <div class=\"title\">测试</div>\n" +
                "\t\t\t\t<div class=\"visible-print text-center\">\n" +
                "\t\t\t\n" +
                "\t\t\t\t\t<img src=\"http://bbqbb.oss-cn-beijing.aliyuncs.com/cczu_file/Uy.jpeg\" style=\"padding:20px;\">  \n" +
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
                "\t\t\t\t2. 打开“扫码签到工具”，点击“扫码签到”，扫码。<br>\n" +
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
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
        //SimpleMailMessage mailMsg = new SimpleMailMessage();//只支持文字
        helper.setFrom(FROM);//发件人
        helper.setTo("1747991245@qq.com");//收件人
        helper.setSubject("很棒哦，今晚你回去扫地了");//邮件标题
        helper.setText(html,true); //测试内容（html）
        try {
            javaMailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
