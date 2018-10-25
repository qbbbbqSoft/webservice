package com.cczu.spider.utils.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;
import java.net.URL;
import java.util.Date;

public class OssFileManager {

    public static void main(String[] args) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIM9JRCQlnKsW2";
        String accessKeySecret = "mkyoK1dUD3NCXaiKUbIJ4NvC323jSb";
        String bucketName = "bbqbb";
        String objectName = "cczu_poem/bg3.jpg";
// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
     /*   OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;;
                System.out.println("\n" + line);
            }
            content.close();
        }
        ossClient.shutdown();*/
//        ObjectListing cczu_poem = ossClient.listObjects("bbqbb");
//        System.out.println(cczu_poem);

        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:/tmp/1.png"));
// 关闭OSSClient。
        ossClient.shutdown();
    }
}
