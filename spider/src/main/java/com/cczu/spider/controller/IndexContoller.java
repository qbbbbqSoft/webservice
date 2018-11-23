package com.cczu.spider.controller;

import com.alibaba.fastjson.JSONObject;
import com.cczu.spider.entity.LectureEntity;
import com.cczu.spider.entity.SysBindingWxEntity;
import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.entity.SysIndexEntity;
import com.cczu.spider.entity.result.R;
import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.OrderAndValue;
import com.cczu.spider.pojo.TermEnum;
import com.cczu.spider.service.*;
import com.cczu.spider.utils.CCZU_spider;
import com.cczu.spider.utils.CCZU_spiderByHtmlUnit;
import com.cczu.spider.utils.CCZU_spiderUtils;
import com.cczu.spider.utils.thirdpart.WeatherUtil;
import com.cczu.spider.utils.thread.CreateTask;
import com.cczu.spider.utils.utilsforgetschoolinfo.SpiderForCheckUserNameAndPassword;
import org.apache.commons.beanutils.BeanUtils;
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
import java.lang.reflect.InvocationTargetException;
import java.time.Year;
import java.util.*;

import static com.cczu.spider.pojo.TermMap.termMap;

@RestController
@RequestMapping(value = "/cczu")
public class IndexContoller {
    private static int onlineCount = 0;
    @Value("${term.startYear}")
    private Integer YEAR;
    @Value("${term.startMonth}")
    private Integer MONTH;
    @Value("${term.startDay}")
    private Integer DAY;

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

    @Autowired
    private SysBindingWxService sysBindingWxService;

    @Autowired
    private SysCourseService sysCourseService;

    @Autowired
    private SysIndexService sysIndexService;



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

    @RequestMapping("/checkUserAndBinding")
    @ResponseBody
    public R checkUser(String username, String password,String openid) {
        SpiderForCheckUserNameAndPassword util = new SpiderForCheckUserNameAndPassword(username,password);
        String result = "";
        try {
            result = util.checkUserNameAndPassword();
            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
            org.json.JSONObject jsonp = jsonObject.getJSONObject("jsonp");
            int code = jsonp.getInt("code");//200 视为成功
            SysBindingWxEntity entity = new SysBindingWxEntity();
            entity.setOpenid(openid);
            entity.setUsername(username);
            entity.setPassword(password);
            boolean exist = sysBindingWxService.getOne(entity);
            if (code == 200 && !exist) {
                String bindTask = CreateTask.createBindTask(username, password, openid);
                entity.setStatus(bindTask);
                entity.setCreatedate(new Date());
                sysBindingWxService.saveSysBindingWxEntity(entity);
                return R.ok().put("data",result).put("result",bindTask);
            } else {
                return R.error("账号不匹配或先解除绑定");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("出了点问题，稍后再试");
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
                return R.error();
        }
        SpiderForCheckUserNameAndPassword util = new SpiderForCheckUserNameAndPassword(url);
        String result = "";
        try {
            result = util.getInfoAboutSchool();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("data",result);
    }

    /**
     * 解除绑定
     * @param openid
     * @return
     */
    @RequestMapping("/relieveBinding")
    public R relieveBinding(String openid) {
        sysCourseService.deleteByOpenid(openid);
        sysBindingWxService.deleteByOpenid(openid);
        return R.ok();
    }

    //ToDo 天气接口https://www.sojson.com/blog/305.html

    @RequestMapping("/getIndexList")
    public R getIndexList(String openid) {
        SysCourseEntity sysCourseEntity = null;
        List<SysIndexEntity> data = sysIndexService.getAll();
        List<SysCourseEntity> course = sysCourseService.getEntitiesByOpenIDAndWeek(openid,getWeek());
        if (course.size() != 0) {
            SysCourseEntity entity = course.get(0);
            if (entity.getRemind() != null) {
                System.out.println(getWeekOfTerm().toString());
                if (entity.getRemind().contains(getWeekOfTerm().toString())) {
                    try {
                        sysCourseEntity = new SysCourseEntity();
                        BeanUtils.copyProperties(sysCourseEntity,entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String weather = "";
        WeatherUtil util = new WeatherUtil();
        try {
            weather = util.getWeather();
        } catch (Exception e) {
            weather = null;
        }
        return R.ok().put("data",data).put("weather",weather).put("course",sysCourseEntity);
    }

    public static Integer getWeek() {
        Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        List<Integer> week = Arrays.asList(new Integer[]{7, 1, 2, 3, 4, 5, 6});
        return week.get(weekday - 1);
    }

    public Integer getWeekOfTerm() {
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(new Date());
        int week_now = cal.get(Calendar.WEEK_OF_YEAR);
        cal2.set(YEAR,MONTH,DAY);
        int week_start = cal2.get(Calendar.WEEK_OF_YEAR);
        return week_now - week_start + 1;
    }

}
