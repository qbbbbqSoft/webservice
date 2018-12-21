package com.cczu.spider.testmodule;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.methods.HttpGet;

public class Test1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        WebClient webClient = new WebClient(BrowserVersion.CHROME);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//
//        webClient.getOptions().setCssEnabled(false);
//        webClient.getOptions().setJavaScriptEnabled(false);
//        try {
//            HtmlPage page = webClient.getPage("http://192.168.23.254:3001/getcurriculum/18416235/240818");
//            System.out.println(page.asXml());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        HttpGet httpGet = new HttpGet("http://192.168.23.254:3001/getcurriculum/18416235/240818");
        System.out.println(httpGet);
        long end = System.currentTimeMillis();
        System.out.println("时间为" + (end - start)/1000 + "s");
    }
}
