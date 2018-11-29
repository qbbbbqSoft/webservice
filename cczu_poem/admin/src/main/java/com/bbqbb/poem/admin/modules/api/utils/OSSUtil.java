package com.bbqbb.poem.admin.modules.api.utils;

import com.aliyun.oss.OSSClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OSSUtil {

    public void asdasd(String token) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("path", "pages/login/index?id=2");
        params.put("width", 430);
        params.put("auto_color", false);
        Map<String,Object> line_color = new HashMap<>();
        line_color.put("r", 0);
        line_color.put("g", 0);
        line_color.put("b", 0);
        params.put("line_color", line_color);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacode?access_token="+token);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String aa = com.alibaba.fastjson.JSON.toJSONString(params);
        StringEntity entity;
        entity = new StringEntity(aa);
        entity.setContentType("image/png");

        httpPost.setEntity(entity);
        HttpResponse response;

        response = httpClient.execute(httpPost);
        InputStream inputStream = response.getEntity().getContent();
//        uploadOSSFile(inputStream);
        File targetFile = new File("/Volumes/Data/pic/");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream("/Volumes/Data/pic/5.png");

        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush();
        out.close();
    }
    public static void uploadOSSFile(InputStream input) throws Exception {
//        private String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // accessKey
//        private String accessKeyId = "LTAIeEMwMEpun5UI";
//        private String accessKeySecret = "2CH6wkjtO7F94qAvJtB3YQ7QtuhqsQ";
//        // 空间
//        private String bucketName = "bbqbb";
//        // 文件存储目录
//        private String filedir = "cczu_poem/";
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIeEMwMEpun5UI";
        String accessKeySecret = "2CH6wkjtO7F94qAvJtB3YQ7QtuhqsQ";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传网络流。
        InputStream inputStream = input;
        ossClient.putObject("bbqbb", "cczu_poem/", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
