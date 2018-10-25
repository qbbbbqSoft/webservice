package com.cczu.spider.utils;

import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Component
public class CCZU_spiderByHtmlUnit {
    public List<CoursePojo<List<OrderAndValue>>> cczuSpider(String inputUserName,String inputPassword,Integer term) throws Exception {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //解决net.sourceforge.htmlunit.corejs.javascript.EcmaError: TypeError: Cannot find function createObjectUR
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);

        long begin = new Date().getTime();
        // 获取Token与Cookie的请求
        String url = "http://jwcas.cczu.edu.cn/login";
        URL link = new URL(url);
        WebRequest request = new WebRequest(link);
//        request.setProxyHost("47.98.105.243");
//        request.setProxyPort(9901);
        webClient.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        webClient.addRequestHeader("Accept-Encoding","gzip, deflate");
        webClient.addRequestHeader("Accept-Language","zh-CN,zh;q=0.8");
        webClient.addRequestHeader("Cache-Control","no-cache");
        webClient.addRequestHeader("Proxy-Connection","keep-alive");
//        webClient.addRequestHeader("Referer","[图片]http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=26&tableName=TABLE26&title=%B9%FA%B2%FA%C6%F7%D0%B5&bcId=118103058617027083838706701567&Id=68315");
        webClient.addRequestHeader("Upgrade-Insecure-Requests","1");
        webClient.addRequestHeader("Host","jwcas.cczu.edu.cn");
        webClient.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
//        webClient.getOptions().setTimeout(8000);
//        Page page = webClient.getPage(request);
        HtmlPage page2 = webClient.getPage(request);
        DomElement username = page2.getElementById("username");
        username.setAttribute("value",inputUserName);
        DomElement password = page2.getElementById("password");
        password.setAttribute("value",inputPassword);
        HtmlInput btn = page2.getFirstByXPath("//*[@id=\"fm1\"]/div[3]/input[4]");
        HtmlPage page3 = btn.click();
        DomNodeList<DomElement> a = page3.getElementsByTagName("a");
//        for (int i = 0; i < a.size(); i++) {
//            DomElement domElement = a.get(i);
//            System.out.println(domElement.asXml());
//        }
        DomElement domElement = a.get(2);
        domElement.click();
        CookieManager cookieManager = webClient.getCookieManager();
        CCZU_spiderUtils cczu_spiderUtils = new CCZU_spiderUtils();
        List<CoursePojo<List<OrderAndValue>>> getinfo = cczu_spiderUtils.getinfo(cookieManager,term);
        long end = new Date().getTime();
        webClient.close();
        return getinfo;
    }
}
