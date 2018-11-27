package com.cczu.spider.utils.utilsforgetschoolinfo;

import com.cczu.spider.pojo.LectureModel;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpiderForLectureTimes {

    private static List<LectureModel> list;

    public List<LectureModel> getLectureTimes(String stuName) throws Exception {
        list = new ArrayList<>();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //解决net.sourceforge.htmlunit.corejs.javascript.EcmaError: TypeError: Cannot find function createObjectUR
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);

        long begin = new Date().getTime();
        // 获取Token与Cookie的请求
        String url = "http://219.230.159.132/web_jxrw/cx_jz_kcxkmd.aspx";
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
        DomElement LBView2 = page2.getElementById("LBView2");
        HtmlPage click = LBView2.click();
        DomElement txtxm = click.getElementById("Txtxm");
        txtxm.setAttribute("value",stuName);
        DomElement btcx = click.getElementById("Btcx");
        HtmlPage click1 = btcx.click();
//        Thread.sleep(5000);
        Document document = Jsoup.parse(click1.asXml());
        Element tabel = document.getElementById("GVxkmd");
        LectureModel model = null;
        if (tabel != null) {
            Elements tr = tabel.getElementsByTag("tr");
            for (int i = 1; i < tr.size(); i++) {
                Elements td = tr.get(i).getElementsByTag("td");
                model = new LectureModel(td.get(0).text(),td.get(1).text(),td.get(2).text(),td.get(3).text(),td.get(4).text(),td.get(5).text(),td.get(6).text());
                list.add(model);
            }
        }
        return list;
//        Elements tr = taabel.getElementsByTag("tr");
//        System.out.println(tabel);
    }
}
