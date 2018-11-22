package com.cczu.spider.utils.utilsforgetschoolinfo;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class SpiderForCheckUserNameAndPassword {
    private String userName;
    private String pwd;
    private String url;

    public SpiderForCheckUserNameAndPassword(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }
    public SpiderForCheckUserNameAndPassword() {

    }

    public SpiderForCheckUserNameAndPassword(String url) {
        this.url = url;
    }

    public static void main(String[] args) throws Exception {
        Connection.Response res = Jsoup.connect("http://mobile.cczu.edu.cn/mp/login?psw=022591&user=13446212")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
//        JSONObject json = JSONObject.fromObject(body);
        System.out.println(body);
    }

    public String checkUserNameAndPassword() throws Exception {
        Connection.Response res = Jsoup.connect("http://mobile.cczu.edu.cn/mp/login?psw="+ this.pwd+ "&user=" + this.userName)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
//        JSONObject json = JSONObject.fromObject(body);
        return body;
    }

    public String getInfoAboutSchool() throws Exception {
        Connection.Response res = Jsoup.connect(this.url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
//        JSONObject json = JSONObject.fromObject(body);
        return body;
    }
}
