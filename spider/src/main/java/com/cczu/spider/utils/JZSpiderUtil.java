package com.cczu.spider.utils;

import com.cczu.spider.entity.LectureEntity;
import com.cczu.spider.repository.LectureRepo;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Component
public class JZSpiderUtil {
    @Autowired
    private LectureRepo lectureRepo;
    public void getAndSaveLectureInfo () throws Exception{
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
        HtmlSelect select = (HtmlSelect)page2.getElementByName("Control_base_xq_dd1$DDxq");
        System.out.println(select);
//        select.setSelectedAttribute("14-15-2",true);
        List<HtmlOption> options = select.getOptions();
        HtmlPage page = select.setSelectedAttribute(options.get(1), true);
        DomElement lbView1 = page.getElementById("LBView1");
        HtmlPage page1 = lbView1.click();
//        DomElement dataList1_ctl14_hyperLink1 = page2.getElementById("DataList1_ctl14_HyperLink1");
//        HtmlPage click = dataList1_ctl14_hyperLink1.click();
//        System.out.println(page1.asText());
        Document document = Jsoup.parse(page1.asXml());
        Element gVkbk = document.getElementById("GVkbk");
        Elements trs = gVkbk.getElementsByTag("tr");
        LectureEntity lectureEntity = null;
        for (int i = 1; i < trs.size(); i++)  {
            lectureEntity = new LectureEntity();
            Elements tds = trs.get(i).getElementsByTag("td");
            lectureEntity.setTerm(tds.get(1).text());
            lectureEntity.setOrdernum(Integer.valueOf(tds.get(2).text()));
            lectureEntity.setClassname(tds.get(3).text());
            lectureEntity.setTeacher(tds.get(4).text());
            lectureEntity.setClasstype(tds.get(5).text());
            lectureEntity.setWeek(Integer.valueOf(tds.get(6).text()));
            lectureEntity.setDay(Integer.valueOf(tds.get(7).text()));
            lectureEntity.setClass1(Integer.valueOf(tds.get(8).text()));
            lectureEntity.setClass2(Integer.valueOf(tds.get(9).text()));
            lectureEntity.setClassroom(tds.get(10).text());
            lectureEntity.setChoosenum(Integer.valueOf(trim(tds.get(11).text()).isEmpty()?"0":tds.get(11).text()));
            lectureEntity.setSchoolcampus(trim(tds.get(12).text()).isEmpty()?"无":tds.get(12).text());
            String text = tds.get(11).text();
//            System.out.println(trim(tds.get(11).text().trim()).isEmpty());
            lectureRepo.save(lectureEntity);
//            List<LectureEntity> all = lectureRepo.findAll();
//            for (Element td: tds) {
//                System.out.println(td.text());
//            }
        }

    }

    /**
     * 用于替换字符串中的中文空格符号
     * @param s
     * @return
     */
    public static String trim(String s){
        String result = "";
        if(null!=s&&!"".equals(s)){
            result=s.replaceAll("^[　*| *| *|//s*]*","").replaceAll("[　*| *| *|//s*]*$","");
        }
        return result;
    }

}
