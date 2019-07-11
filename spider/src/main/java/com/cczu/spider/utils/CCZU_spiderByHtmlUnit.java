package com.cczu.spider.utils;

import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import com.cczu.spider.pojo.ScoreModel;
import com.cczu.spider.repository.SysCourseRepo;
import com.cczu.spider.utils.thread.CreateTask;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CCZU_spiderByHtmlUnit")
public class CCZU_spiderByHtmlUnit {

    @Autowired
    private SysCourseRepo sysCourseRepo;

    @Autowired
    private CCZU_spiderUtils cczu_spiderUtils;
    public List<CoursePojo<List<OrderAndValue>>> cczuSpider(String inputUserName,String inputPassword,Integer term, String openid) throws Exception {
        System.out.println("开始抓数据" + new Date());
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
//        CCZU_spiderUtils cczu_spiderUtils = new CCZU_spiderUtils();
        List<CoursePojo<List<OrderAndValue>>> getinfo = cczu_spiderUtils.getinfo(cookieManager,term,openid);
        long end = new Date().getTime();
        webClient.close();
        return getinfo;
    }

    public List<CoursePojo<List<OrderAndValue>>> cczuSpiderWithNode(String inputUserName,String inputPassword,Integer term, String openid,Integer type) throws Exception {
        Connection.Response res = Jsoup.connect("http://localhost:3000/api/getcourse?stuNum="+ inputUserName + "&password=" + inputPassword)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
        System.out.println(body);
        Document document = Jsoup.parse(body);
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
                    course[i][j] = "暂时没有课，休息一下吧!";
                    orderAndValue.setOrder(j);
                    orderAndValue.setValue("暂时没有课，休息一下吧!");
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
        if (type == 2) {
            SysCourseEntity entity;
            for (int i = 1; i < 8; i++) {
                entity = new SysCourseEntity();
                for (int j = 0; j < elementsByClass.size(); j++) {
                    entity.setWeek(i);
                    switch (j) {
                        case 0:
                            entity.setCourse1(course[0][i]);
                            break;
                        case 1:
                            entity.setCourse2(course[1][i]);
                            break;
                        case 2:
                            entity.setCourse3(course[2][i]);
                            break;
                        case 3:
                            entity.setCourse4(course[3][i]);
                            break;
                        case 4:
                            entity.setCourse5(course[4][i]);
                            break;
                        case 5:
                            entity.setCourse6(course[5][i]);
                            break;
                        case 6:
                            entity.setCourse7(course[6][i]);
                            break;
                        case 7:
                            entity.setCourse8(course[7][i]);
                            break;
                        case 8:
                            entity.setCourse9(course[8][i]);
                            break;
                        case 9:
                            entity.setCourse10(course[9][i]);
                            break;
                        case 10:
                            entity.setCourse11(course[10][i]);
                            break;
                        case 11:
                            entity.setCourse12(course[11][i]);
                            break;
                    }
                    entity.setOpenid(openid);
                    entity.setCreatedate(new Date());
                    sysCourseRepo.save(entity);
                }
            }
            System.out.println("数据操作结束开始线程的时间" + new Date());
            String task = CreateTask.createTask(openid);
        }
        System.out.println("返回数据的时间" + new Date());
        return coursePojos;
    }

    public List<ScoreModel> getScore(String inputUserName,String inputPassword) throws Exception {
        Connection.Response res = Jsoup.connect("http://localhost:3000/api/getscore?stuNum="+ inputUserName + "&password=" + inputPassword)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
        List<ScoreModel> result = new ArrayList<>();
        ScoreModel model = null;
        Document document = Jsoup.parse(body);
        Element table = document.getElementById("gvcj1");
        if (table == null) {
            return result;
        }
        Elements trs = table.getElementsByTag("tr");
        for (Element tr:trs) {
            Elements tds = tr.getElementsByTag("td");
            if (tds.size() != 0) {
                model = new ScoreModel();
                model.setStuNum(tds.get(0).text());
                model.setName(tds.get(1).text());
                model.setTerm(tds.get(2).text());
                model.setCourseName(tds.get(3).text());
                model.setType(tds.get(4).text());
                model.setCredit(tds.get(5).text());
                model.setGrade(tds.get(6).text());
                model.setExamType(tds.get(7).text());
                model.setaPoint(tds.get(8).text());
                model.setCourseNum(tds.get(9).text());
                model.setClassHour(tds.get(10).text());
                result.add(model);
            }
        }
        return result;
    }
}
