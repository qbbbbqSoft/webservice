package com.cczu.spider.testmodule;

import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        Connection.Response res = Jsoup.connect("http://192.168.23.254:3001/getcurriculum/17401124/01525X")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
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
        long end = System.currentTimeMillis();
        System.out.println("时间为" + (end - start)/1000 + "s");
    }
}
