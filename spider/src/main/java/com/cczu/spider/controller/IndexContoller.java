package com.cczu.spider.controller;

import com.alibaba.fastjson.JSONObject;
import com.cczu.spider.entity.LectureEntity;
import com.cczu.spider.entity.result.R;
import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import com.cczu.spider.pojo.TermEnum;
import com.cczu.spider.pojo.TermMap;
import com.cczu.spider.service.LectureService;
import com.cczu.spider.service.MailService;
import com.cczu.spider.service.RedisService;
import com.cczu.spider.service.UpImgService;
import com.cczu.spider.utils.CCZU_spider;
import com.cczu.spider.utils.CCZU_spiderByHtmlUnit;
import com.cczu.spider.utils.CCZU_spiderUtils;
import com.cczu.spider.utils.thread.CreateTask;
import com.cczu.spider.utils.utilsforgetschoolinfo.SpiderForCheckUserNameAndPassword;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cczu.spider.pojo.TermMap.termMap;

@RestController
@RequestMapping(value = "/cczu")
public class IndexContoller {
    private static int onlineCount = 0;
    @Autowired
    private UpImgService upImgService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private CCZU_spiderByHtmlUnit cczu_spiderByHtmlUnit;



    @Value("${ChromeDriver_Path_win}")
    private String ChromeDriver_Path_win;
    @Value("${ChromeDriver_Path_linux}")
    private String ChromeDriver_Path_linux;

    private JSONObject json = new JSONObject();
    @RequestMapping(value = "/course")
    public String[][] course() {
        CCZU_spider cczu_spider = new CCZU_spider();
        System.out.println(ChromeDriver_Path_win);
        String[][] strings = cczu_spider.cczuSpider("13446212", "022591", "14-15-2",ChromeDriver_Path_linux);
        return strings;
    }

    @RequestMapping(value = "/coursebyhtmlunit")
    public List<CoursePojo> courseByHtmlunit(@RequestParam(value = "userName",required = true) String userName,
                                             @RequestParam(value = "password",required = true) String password,
                                             @RequestParam(value = "term",required = false,defaultValue = "T1819_1") String term) throws Exception {
        long startTime=System.currentTimeMillis();   //获取开始时间
        GenericObjectPoolConfig jredisConfig = new GenericObjectPoolConfig();
        try {
            String termE = TermEnum.valueOf(term).getName();
            Integer index = termMap.get(termE);
//            String result = redisService.get(userName);
            JedisPool pool = new JedisPool(jredisConfig, "47.98.105.243",6379,0,"root",0);
            Jedis redis = pool.getResource();
            String result = redis.get(userName);
            List<CoursePojo> pojos = json.parseArray(result, CoursePojo.class);
            if (pojos == null){
//                CCZU_spiderByHtmlUnit cczu_spiderByHtmlUnit = new CCZU_spiderByHtmlUnit();
                List<CoursePojo<List<OrderAndValue>>> strings = cczu_spiderByHtmlUnit.cczuSpider(userName,password,index,"");
                redis.set(userName, json.toJSONString(strings));
                String result2 = redis.get(userName);
                List<CoursePojo> pojos2 = json.parseArray(result2, CoursePojo.class);
                long endTime=System.currentTimeMillis();
                System.out.println("程序共用时：" + (endTime - startTime)/1000 + "s");
                mailService.sendMail("获取课表数据成功","账号为" + userName + "获取课表信息成功,共耗时间" + (endTime - startTime)/1000 + "s");
                return pojos2;
            } else {
                return pojos;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mailService.sendMail("错误提醒","账号为" + userName + "正在抓取课表的数据时发生错误!=========\n" + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/check")
    private boolean check(HttpServletRequest request, String userName, String password) throws Exception {
        GenericObjectPoolConfig jredisConfig = new GenericObjectPoolConfig();
        try {
            mailService.sendMail("用户进行用户验证","账号为" + userName + "正在尝试验证登录课表小程序");
            JedisPool req = new JedisPool(jredisConfig, "47.98.105.243",6379,0,"root",2);
            Jedis reqResource = req.getResource();
            reqResource.set("request" + userName,password);
            JedisPool pool = new JedisPool(jredisConfig, "47.98.105.243",6379,0,"root",1);
            Jedis redis = pool.getResource();
            String res = redis.get(userName);
            if (res == null) {
                CCZU_spiderUtils cczu_spiderUtils = new CCZU_spiderUtils();
                boolean b = cczu_spiderUtils.checkLogin(userName, password);
                if (b) {
                    redis.set(userName,password);
                    return true;
                } else
                    return false;
            } else {
                boolean equals = res.equals(password);
                if (!equals) {
                    CCZU_spiderUtils cczu_spiderUtils = new CCZU_spiderUtils();
                    boolean b = cczu_spiderUtils.checkLogin(userName, password);
                    if (b) {
                        redis.set(userName,password);
                        return true;
                    } else
                        return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            mailService.sendMail("错误提醒","账号为" + userName + "正在尝试验证登录课表小程序时发生错误!=========\n" + e.getMessage());
            return false;
        }
    }



    @RequestMapping(value = "/headImgUpload")
    @ResponseBody
    public String headImgUpload(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image != null) {//如果image=null 表示上传的不是图片格式
                System.out.println(image.getWidth());//获取图片宽度，单位px
                System.out.println(image.getHeight());//获取图片高度，单位px
                value.put("height",image.getWidth());
                value.put("width",image.getWidth());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String url = upImgService.updateHead(file);
            value.put("data", url);
            value.put("code", 0);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            value.put("code", 500);
            value.put("msg", "图片上传成功");
        }
        return JSONObject.toJSONString(value);
    }

    @RequestMapping(value = "/getAndSaveLectureInfo")
    @ResponseBody
    public String getAndSaveLectureInfo() {
        lectureService.getAndSaveLectureInfo();
        return "ok";
    }

    @RequestMapping(value = "/getLectureInfo")
    @ResponseBody
    public Page<LectureEntity> getLectureInfo() {
        return lectureService.getLectureInfo();
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public R checkUser(String username, String password,String openid) {
        SpiderForCheckUserNameAndPassword util = new SpiderForCheckUserNameAndPassword(username,password);
        String result = "";
        try {
            result = util.checkUserNameAndPassword();
            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
            org.json.JSONObject jsonp = jsonObject.getJSONObject("jsonp");
            int code = jsonp.getInt("code");//200 视为成功
            if (code == 200) {
                String bindTask = CreateTask.createBindTask(username, password, openid);
                return R.isOk().data(result).data(bindTask);
            } else {
                return R.isFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.isFail();
        }

    }

    /**
     *type   1。学校概况 2。院系介绍 3。校史沿革
     * @return
     */
    @RequestMapping("/getInfoAboutSchool/{type}")
    @ResponseBody
    public R getInfoAboutSchool(@PathVariable("type") Integer type) {
        String url = "";
        switch (type){
            case 1:
                url = "http://mobile.cczu.edu.cn/mp/apps/introduRest/index";
                break;
            case 2:
                url = "http://mobile.cczu.edu.cn/mp/apps/introduRest/getDeparts";
                break;
            case 3:
                url = "http://mobile.cczu.edu.cn/mp/apps/introduRest/updateHistory";
                break;
            default:
                return R.isFail();
        }
        SpiderForCheckUserNameAndPassword util = new SpiderForCheckUserNameAndPassword(url);
        String result = "";
        try {
            result = util.getInfoAboutSchool();
        } catch (Exception e) {
            e.printStackTrace();
            return R.isFail();
        }
        return R.isOk().data(result);
    }



}
