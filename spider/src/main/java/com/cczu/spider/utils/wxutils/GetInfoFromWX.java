package com.cczu.spider.utils.wxutils;

import com.cczu.spider.service.UpImgService;
import com.cczu.spider.service.impl.UpImgServiceImpl;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GetInfoFromWX {

    public static void main(String[] args) throws Exception {
        Connection.Response res = Jsoup.connect("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcb506c516f5ee36d&secret=0b81a9888f3972585ecc837d8a950324")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();
        String body = res.body();
        JSONObject json = new JSONObject(body);
        String access_token = json.getString("access_token");
//        Map<String, Object> params = new HashMap<>();
//        params.put("path", "pages/login/index?id=2");
//        params.put("width", 430);
//        params.put("auto_color", false);
//        Map<String,Object> line_color = new HashMap<>();
//        line_color.put("r", 0);
//        line_color.put("g", 0);
//        line_color.put("b", 0);
//        params.put("line_color", line_color);
//
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacode?access_token="+access_token);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
//        String aa = com.alibaba.fastjson.JSON.toJSONString(params);
//        StringEntity entity;
//        entity = new StringEntity(aa);
//        entity.setContentType("image/png");
//
//        httpPost.setEntity(entity);
//        HttpResponse response;
//
//        response = httpClient.execute(httpPost);
//        InputStream inputStream = response.getEntity().getContent();
//
//        File targetFile = new File("/Volumes/Data/pic/");
//        if(!targetFile.exists()){
//            targetFile.mkdirs();
//        }
//        FileOutputStream out = new FileOutputStream("/Volumes/Data/pic/5.png");
//
//        byte[] buffer = new byte[8192];
//        int bytesRead = 0;
//        while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
//            out.write(buffer, 0, bytesRead);
//        }
//
//        out.flush();
//        out.close();
        getminiqrQr(access_token);
        System.out.println(body);
    }

    public static Map getminiqrQr(String accessToken) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
            Map<String,Object> param = new HashMap<>();
            param.put("path", "pages/login/index");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            System.out.println("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            System.out.println(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);

            File file = new File("/Volumes/Data/pic/1.png");
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久小程序码URL接口异常" + e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void getAndSaveWXQrCode(String token, String fileName, String filePath) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("scene",fileName);
        params.put("page", "pages/login/index");
        params.put("width", 430);
        params.put("auto_color", false);
        Map<String,Object> line_color = new HashMap<>();
        line_color.put("r", 0);
        line_color.put("g", 0);
        line_color.put("b", 0);
        params.put("line_color", line_color);
        params.put("is_hyaline",true);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String aa = com.alibaba.fastjson.JSON.toJSONString(params);
        StringEntity entity;
        entity = new StringEntity(aa);
        entity.setContentType("image/png");

        httpPost.setEntity(entity);
        HttpResponse response;

        response = httpClient.execute(httpPost);
        InputStream inputStream = response.getEntity().getContent();

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName +".png");

        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
        out.close();
        UpImgService service = new UpImgServiceImpl();
        try {
            service.updateErWeiMa(filePath + fileName +".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
