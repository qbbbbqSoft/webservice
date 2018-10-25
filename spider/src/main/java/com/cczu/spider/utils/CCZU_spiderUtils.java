package com.cczu.spider.utils;

import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CCZU_spiderUtils {

    public List<CoursePojo<List<OrderAndValue>>> getinfo(CookieManager cookieManager,Integer index) throws Exception{
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //解决net.sourceforge.htmlunit.corejs.javascript.EcmaError: TypeError: Cannot find function createObjectUR
        webClient.getOptions().setThrowExceptionOnScriptError(false);


        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setCookieManager(cookieManager);
        HtmlPage page = webClient.getPage("http://219.230.159.132/login7_jwgl.aspx");
        DomElement dataList1_ctl02_hyperLink1 = page.getElementById("DataList1_ctl02_HyperLink1");
        HtmlPage page1 = dataList1_ctl02_hyperLink1.click();
//        DomElement dDxq = page1.getElementById("DDxq");
//        dDxq.setAttribute("value","14-15-2");
//        HtmlAnchor anchorByText = page1.getAnchorByText("14-15-2");
        HtmlSelect select = (HtmlSelect) page1.getElementById("DDxq");
//        select.setSelectedAttribute("14-15-2",true);
        select.setSelectedIndex(index);
        DomElement btcx = page1.getElementById("Btcx");
        HtmlPage htmlPage = btcx.click();
//        String page2 = btcx.click().getWebResponse().getStatusMessage();
        Thread.sleep(10000);
        Document document = Jsoup.parse(htmlPage.asXml());
        Element gVxkkb = document.getElementById("GVxkkb");
        Elements elementsByClass = gVxkkb.getElementsByClass("dg1-item");
        Element c;
        String[][] course = new String[elementsByClass.size()][8];
        List<CoursePojo<List<OrderAndValue>>> coursePojos = new ArrayList<>();
        CoursePojo<List<OrderAndValue>> coursePojo;
        List<OrderAndValue> orderAndValues;
        OrderAndValue orderAndValue;
        for (int i = 0; i < elementsByClass.size(); i++) {
            coursePojo = new CoursePojo<>();
            orderAndValues = new ArrayList<>();
            c = elementsByClass.get(i);
            for (int j = 1; j < 8; j++) {
                orderAndValue = new OrderAndValue();
                Element element = c.getElementsByTag("td").get(j);
                int size = element.text().length();
//                    System.out.println(i + "_" +j+element.text());
                if (size == 1) {
                    course[i][j] = "没有课yoo~~!";
                    orderAndValue.setOrder(j);
                    orderAndValue.setValue("没有课yoo~~!");
                }
                else {
                    course[i][j] = element.text();
                    orderAndValue.setOrder(j);
                    orderAndValue.setValue(element.text());
                }
                orderAndValues.add(orderAndValue);
                coursePojo.setWeek(i);
                coursePojo.setData(orderAndValues);
            }
            coursePojos.add(coursePojo);
        }
        for (int i = 0; i < elementsByClass.size(); i++) {
            System.out.println(course[i][1]);
        }
        webClient.close();
        return coursePojos;
    }

    public boolean checkLogin(String inputUserName,String inputPassword) throws Exception{
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
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
        webClient.getOptions().setTimeout(8000);
//        Page page = webClient.getPage(request);
        HtmlPage page2 = webClient.getPage(request);
        DomElement username = page2.getElementById("username");
        username.setAttribute("value",inputUserName);
        DomElement password = page2.getElementById("password");
        password.setAttribute("value",inputPassword);
        HtmlInput btn = page2.getFirstByXPath("//*[@id=\"fm1\"]/div[3]/input[4]");
        HtmlPage page3 = btn.click();
        DomElement msg = page3.getElementById("msg");
        if (msg != null) {
            return false;
        } else {
            return true;
        }
    }
}
