package com.cczu.spider.controller;


import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.entity.SysWxUserInfoEntity;
import com.cczu.spider.entity.result.R;
import com.cczu.spider.pojo.SysActivityAndSysSignUpModel;
import com.cczu.spider.service.SysActivityService;
import com.cczu.spider.service.SysSignUpService;
import com.cczu.spider.service.SysWxUserInfoService;
import com.cczu.spider.utils.erweima.ZXingCode;
import com.cczu.spider.utils.redis.RedisUtils;
import com.cczu.spider.utils.wxutils.GetInfoFromWX;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/cczu")
@Api(value = "签到相关")
public class SignUpController {
    @Value("${FILE.PATH}")
    private String filePath;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysActivityService sysActivityService;

    @Autowired
    private SysSignUpService sysSignUpService;

    @Autowired
    private SysWxUserInfoService sysWxUserInfoService;

    /**
     * 创建活动生成二维码
     * @param entity
     * @return
     */
    @PostMapping("/createWXQrCode")
    @ResponseBody
    @ApiOperation(value = "新建活动",notes = "新建活动生成二维码并保存")
    public R createWxQrCode(@RequestBody SysActivityEntity entity) {
        String token = redisUtils.get("access_token");
        GetInfoFromWX getInfoFromWX = new GetInfoFromWX();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + "";
        if (token != null) {
            try {
                getInfoFromWX.getAndSaveWXQrCode(token,name,filePath);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error("失败");
            }
        } else {
            try {
                Connection.Response res = Jsoup.connect("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcb506c516f5ee36d&secret=0b81a9888f3972585ecc837d8a950324")
                        .header("Accept", "*/*")
                        .header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                        .timeout(10000).ignoreContentType(true).execute();//.get();
                String body = res.body();
                org.json.JSONObject json = new org.json.JSONObject(body);
                token = json.getString("access_token");
                int expires_in = json.getInt("expires_in");
                redisUtils.set("access_token",token,expires_in);
                getInfoFromWX.getAndSaveWXQrCode(token,name,filePath);
            }catch (Exception e) {
                e.printStackTrace();
                return R.error("失败");
            }
        }
//        SysActivityEntity entity = new SysActivityEntity();
//        entity.setActivityName(activityName);
//        entity.setActivityDate(new Date());
        entity.setActivityID(name);
//        entity.setOrganizingPeopleOpenID(openid);
//        entity.setActivityOrganizingPeople("cczu");
//        entity.setActivityPlace("anywhere这里");
        entity.setActivityQrCodeUrl("https://bbqbb.oss-cn-beijing.aliyuncs.com/cczu_poem/" + name + ".png");
        entity.setActivityBackgroundPic("https://bbqbb.oss-cn-beijing.aliyuncs.com/cczu_poem/" + name + ".png");
        entity.setActivityStatus(0);
        entity.setCreateDate(new Date());
        sysActivityService.save(entity);
        return R.ok();
    }

    @PostMapping("editActivity")
    @ApiOperation(value = "修改活动",notes = "传入的实体类ID不为空不为0，进行保存操作")
    public R editActivity(@RequestBody SysActivityEntity entity) {
        sysActivityService.save(entity);
        return R.ok();
    }
    /**
     * 获取自己创建的活动
     * @param openid
     * @return
     */
    @GetMapping("/getSysActivityListByOpenid")
    @ResponseBody
    @ApiOperation(value = "获取自己创建的活动",notes = "微信openid必须传入")
    public R getSysActivityListByOpenid(@ApiParam(value = "微信openid", required = true, defaultValue = "")@RequestParam(value = "openid",required = true,defaultValue = "123456") String openid) {
        List<SysActivityEntity> list = sysActivityService.getSysActivityListByOpenid(openid);
        return R.ok().put("data",list);
    }

    /**
     * 活动的详情
     * @param ID
     * @return
     */
    @GetMapping("/getOneActivityDetailByID")
    @ResponseBody
    @ApiOperation(value = "活动的详情",notes = "ID为活动的主键ID")
    public R getOneActivityDetailByID(@ApiParam(value = "活动的主键ID",required = true,defaultValue = "1")@RequestParam(value = "ID",required = true, defaultValue = "1") Long ID) {
        SysActivityEntity one = sysActivityService.getOneByID(ID);
        List<SysSignUpEntity> signUpEntities = sysSignUpService.getSysSignUpEntitiesByActivityID(one.getActivityID());
        return R.ok().put("data",one).put("allSignUpInfo",signUpEntities).put("count",signUpEntities.size());
    }

    /**
     * 设置活动的状态：默认0创建成功，1开始签到，2开始签退，3活动结束
     * @param ID
     * @param status
     * @return
     */
    @GetMapping("/setActivityStatusByID")
    @ResponseBody
    @ApiOperation(value = "设置活动的状态",notes = "默认0创建成功，1开始签到，2结束签到，3开始签退，4结束签退（可以修改状态），5活动结束（状态不可修改）")
    public R setActivityStatusByID(@ApiParam(value = "活动主键ID",required = true,defaultValue = "1")@RequestParam(value = "ID",required = true,defaultValue = "1") Long ID,
                                   @ApiParam(value = "设置的状态",required = true,defaultValue = "1")@RequestParam(value = "status",required = true,defaultValue = "1") Integer status) {
        sysActivityService.setActivityStatusByID(ID,status,new Date());
        return R.ok();
    }

    /**
     * 个人参加的活动列表
     * @param openid
     * @return
     */
    @GetMapping("/queryTakePartInActivityByOpenid")
    @ResponseBody
    @ApiOperation(value = "个人参加的活动列表",notes = "微信openid必须传入，返回数据为list，null时返回空数组")
    public R queryTakePartInActivityByOpenid(@ApiParam(value = "微信openid", required = true, defaultValue = "")@RequestParam(value = "openid",required = true,defaultValue = "123456") String openid) {
        List<SysActivityEntity> sysActivityEntities = sysActivityService.queryTakePartInActivityByOpenid(openid);
        return R.ok().put("data",sysActivityEntities);
    }

    @GetMapping("/getOneSysSignUpEntityByActivityIDAndOpenid")
    @ApiOperation(value = "获取已经签到的活动的签到信息",notes = "根据活动的ActivityId的ID和微信的Openid获取已经签到的活动信息")
    public R getOneSysSignUpEntityByActivityIDAndOpenid(@ApiParam(value = "活动的activity",required = true,defaultValue = "1543545308428") @RequestParam(value = "activityID",required = true,defaultValue = "1543545308428")String activityID,
                                                        @ApiParam(value = "微信的Openid",required = true,defaultValue = "123456")@RequestParam(value = "openid",required = true,defaultValue = "123456")String openid){
        SysSignUpEntity entity = sysSignUpService.getSysSignUpEntityByActivityIDAndOpenid(activityID, openid);
        return R.ok().put("data",entity);
    }

    @GetMapping("/getOneSysActivityByActivityID")
    @ApiOperation(value = "扫码之后先获取活动的信息", notes = "扫码能获得活动的activityID，再获取activity的具体信息")
    public R getOneSysActivityByActivityID(@ApiParam(value = "生成的activityID",required = true,defaultValue = "") @RequestParam("activityID")String activityID) {
        SysActivityEntity sysActivityEntity = sysActivityService.getOneByActivityID(activityID);
        return R.ok().put("data",sysActivityEntity);
    }

    /**
     * 签到，签退
     * @param entity
     * @return
     */
    @PostMapping("/signUp")
    @ResponseBody
    @ApiOperation(value = "签到，签退")
    public R signUp(@RequestBody SysSignUpEntity entity) {
        SysActivityEntity sysActivityEntity = sysActivityService.getOneByActivityID(entity.getActivityID());
        if (sysActivityEntity == null) {
            return R.error("no this activity");
        } else {
            //签到人员
            SysSignUpEntity sysSignUpEntity = sysSignUpService.getOneByOpenidAndActivityID(entity.getOpenid(), entity.getActivityID());
            SysWxUserInfoEntity sysWxUserInfoEntity = sysWxUserInfoService.getOneWxUserInfoByOpenid(entity.getOpenid());
            if (sysActivityEntity.getActivityStatus() == 0) {
                return R.error("unstart");
            } else if(sysActivityEntity.getActivityStatus() == 1) {
                if (sysSignUpEntity == null) {
                    //ToDo 保存操作
                    //Done
                    int count = sysSignUpService.getTakePartInUserCount(entity.getActivityID());
                    if (count < sysActivityEntity.getCount()) {
                        entity.setOpenid(sysWxUserInfoEntity.getOpenid());
                        entity.setWxheadimageurl(sysWxUserInfoEntity.getWxheadimageurl());
                        entity.setWxotherinfo(sysWxUserInfoEntity.getWxotheruserinfo());
                        entity.setWxusername(sysWxUserInfoEntity.getWxusername());
                        entity.setStatus(false);
                        entity.setSigndate(new Date());
                        entity.setCreatedate(new Date());
                        sysSignUpService.save(entity);
                        return R.ok("signUp success");
                    } else {
                       return R.error("签到人数已经达到最大值");
                    }
                } else {
                    return R.error("already signUp");
                }
            } else if (sysActivityEntity.getActivityStatus() == 2) {
                return R.error("活动正在进行中");
            }else if(sysActivityEntity.getActivityStatus() == 3) {
                //签退的情况
                if (sysSignUpEntity == null) {
                    return R.error("unsignUp");
                } else {
                    //ToDo 保存操作
                    sysSignUpEntity.setStatus(true);
                    sysSignUpEntity.setLeavedate(new Date());
                    sysSignUpEntity.setUpdatedate(new Date());
                    sysSignUpService.save(sysSignUpEntity);
                    return R.ok("signBack success");
                }
            } else if (sysActivityEntity.getActivityStatus() == 4) {
                return R.error("activity already fininshed");
            } else {
                return R.error("activity already fininshed");
            }
        }
    }

    @RequestMapping(value = "/downloadColorQrcode")
    private void getColorQrcode(String content, String color, HttpServletRequest request, HttpServletResponse response){
        try {
            int size = 166;
            OutputStream stream = response.getOutputStream();
            ZXingCode.getColorQRCode(content, stream, size,color);
        }
        catch ( Exception ex){
            ex.printStackTrace();
        }

    }
}
