package com.bbqbb.poem.admin.modules.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbqbb.poem.admin.common.utils.HttpClientUtil;
import com.bbqbb.poem.admin.common.utils.JsonUtils;
import com.bbqbb.poem.admin.common.utils.Query;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysWxuserinfoService;
import com.bbqbb.poem.admin.modules.api.model.*;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import com.bbqbb.poem.admin.modules.api.utils.DateTransUtil;
import com.bbqbb.poem.admin.modules.api.utils.OSSUtil;
import com.bbqbb.poem.common.exception.RRException;
import com.bbqbb.poem.common.utils.R;
import com.bbqbb.poem.common.utils.RedisUtils;
import com.bbqbb.poem.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/poem/api")
@Api(value = "数据保存和获取的接口")
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiService apiService;
    @Autowired
    private SysWxuserinfoService sysWxuserinfoService;

    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value = "word数据的分页获取")
    @ApiImplicitParam(name = "params", value = "chakan", required =true, dataType ="java.util.Map<java.lang.String, java.lang.Object>")
    @PostMapping("getTitleList")
    public R getTitleList(@RequestBody Map<String,Object> params) {
        Query query = new Query(params);
        List<SysTitleModel> titleList = apiService.getTitleList(query);
        return R.ok().put("data",titleList);
    }

    @ApiOperation("根据ID查看某条的word的内容")
    @GetMapping("/getTitleByTitleID/{ID}")
    public R getTitleDetailByID(@PathVariable("ID")Long ID) {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",ID);
        param.put("titleID",ID);
        SysTitleEntity titleDetailByID = apiService.getTitleDetailByID(param);
        if (titleDetailByID.getCreatedate() != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                titleDetailByID.setLabel(DateTransUtil.dateToStamp(simpleDateFormat.format(titleDetailByID.getCreatedate())));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.ok().put("titleDetail",titleDetailByID);
    }

    @ApiOperation("根据TitleID查看某条word的评论")
    @RequestMapping(value = "/getCommentDetailByTitleID",method = RequestMethod.POST)
    public R getCommentDetailByTitleID(@RequestBody Map<String,Object> params) {
        Query query = new Query(params);
        List<SysCommentEntity> commentDetailByTitleID = apiService.getCommentDetailByTitleID(query);
        return R.ok().put("commentDetailList", commentDetailByTitleID);
    }


    @ApiOperation(value = "信息添加")
    @RequestMapping(value = "/insertTitleDetail",method = RequestMethod.POST)
    public R insertTitleDatail(@RequestBody SysTitleEntity entity) {
        try
        {
            ValidatorUtils.validateEntity(entity);
        }
        catch (RRException e)
        {
            return R.error(e.getMessage());
        }
        entity.setTitle("controller");
        entity.setImageurl("url");
        entity.setImageheight(300);
        entity.setImagewidth(300);
        entity.setContent("asdasdasdas");
        entity.setPrivatestatus(0);
        entity.setAuthor("author");
        entity.setCreatedate(new Date());
        int result = apiService.insertSysTitleDetail(entity);
        return R.ok().put("result",result);
    }
    @RequestMapping("/checkPrivateZoneByZoneCode/zoneCode")
    public R checkPrivateZoneByZoneCode (@PathVariable("zoneCode") String zoneCode) {

        return R.ok();
    }

    @RequestMapping(value = "/postsmt",method = RequestMethod.POST)
    public R post(@RequestBody Map<String, Object> reqbody) throws InvocationTargetException, IllegalAccessException {

        Object obj = null;
        Class<?> sysTitleEntityClass = SysTitleEntity.class;
        try {
            obj = sysTitleEntityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.populate(obj,reqbody);
        SysTitleEntity entity = (SysTitleEntity)obj;
        String page = "pages/kf/index";
        entity.setCreatedate(new Date());
        int result = apiService.insertSysTitleDetail(entity);
        if (entity.getZoneid() != 0) {
            SysZoneEntity sysZoneEntity = new SysZoneEntity();
            sysZoneEntity.setId(entity.getZoneid());
            SysZoneEntity zoneEntity = apiService.getSysZoneEntity(sysZoneEntity);
            page = page + "?type=private&code=" + zoneEntity.getZonecode();
        } else {
            page = page + "?type=public&code=0";
        }
        String urlForToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcb506c516f5ee36d&secret=0b81a9888f3972585ecc837d8a950324";
        String tokenModelStr = HttpClientUtil.doGet(urlForToken);
        TokenModel tokenModel = JsonUtils.jsonToPojo(tokenModelStr, TokenModel.class);
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",reqbody.get("openID").toString());
        jsonObject.put("template_id","yfhS6hfi0dqilZx5MjjrjEY2QMImUnPm2-VpliY4zfQ");
        jsonObject.put("page",page);
        jsonObject.put("form_id",reqbody.get("formID").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        String now = sdf.format(new Date());
        String data = "{\"keyword1\":{\"value\":\"" + entity.getTitle() +"\"},\"keyword2\":{\"value\":\"删除码为" + entity.getDelCode()+ "，是你删除此word的唯一凭证\" },\"keyword3\":{\"value\":\"" + now + "\"}}";
        jsonObject.put("data",data);
        jsonObject.put("emphasis_keyword", "keyword1.DATA");
//        JSONObject data2 = new JSONObject(reqbody);
//        JSONObject data = JSON.parseObject(data2.get("data"));
//        //把每个详细数据转化成JSON,fisrt,keyword1,keyword2....,remark
//        Set<String> keySet = reqbody.keySet();
//        for(String key:keySet)
//        {
//            //吧具体数据转化成JSON然后重新放回去
//            if (key.equals("data")) {
//                HashMap<String,Object> data1 = (HashMap<String, Object>) reqbody.get("data");
//                Set<String> set = data1.keySet();
//                for(String key2:set) {
//                    String keyvalue = data1.get(key2).toString();
//                    data.put(key2, keyvalue);
//                }
//
//            }
//        }
//        jsonObject.put("data", data);
        //最后整体数据进行转化为JSON格式用于传递给微信使用
        String argsJSONStr = JSON.toJSONString(jsonObject);
        logger.info(argsJSONStr);
        String templateUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + tokenModel.getAccess_token();
        params.put("data",argsJSONStr);
        System.out.println(argsJSONStr.replaceAll("=",":"));
        System.out.println(argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        String sendTemplateResult = HttpClientUtil.doPostJson(templateUrl,argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        logger.info(sendTemplateResult);
        System.out.println(sendTemplateResult);
//        body: {
//            touser: '触发帐号的opened',
//                    template_id: '模版id',
//                    page: '点击模版卡片的跳转页面',
//                    form_id: 'form_id或者prepay_id',
//                    data: {
//                keyword1:{
//                    value: '小程序测试模版',
//                            color: '#173177'
//                },
//                keyword2:{
//                    value: '2017年3月24日',
//                            color: '#173177'
//                },
//                keyword3:{
//                    value: 'iHleath',
//                            color: '#173177'
//                }
//            };

        return R.ok().put("data", result);
    }


    //Todo 传入wxuserinfo并保存
    @PostMapping("/wxLogin")
    public R wxLogin(@RequestBody WxUserInfoModel wxUserInfoModel) {

        System.out.println("wxlogin - code: " + wxUserInfoModel.getCode());

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        //课表
        param.put("appid", "wxcb506c516f5ee36d");
        param.put("secret", "0b81a9888f3972585ecc837d8a950324");
        //测试
//        param.put("appid", "wxa6c1aeeb2e756c68");
//        param.put("secret", "6fbc52179cb10926dffbc44ceee049b9");
        param.put("js_code", wxUserInfoModel.getCode());
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);
        WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);

        // 存入session到redis
        redisUtils.set("user-redis-session:" + model.getOpenid(),
                model.getSession_key(),
                60 * 5);
        SysWxuserinfoEntity entity = new SysWxuserinfoEntity(model.getOpenid(),wxUserInfoModel.getSysWxuserinfoEntity().getWxheadimageurl(),wxUserInfoModel.getSysWxuserinfoEntity().getWxusername(),wxUserInfoModel.getSysWxuserinfoEntity().getWxotheruserinfo(),new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("openid",model.getOpenid());
        List<SysWxuserinfoEntity> sysWxuserinfoEntities = apiService.checkUserIfExist(map);
        if (sysWxuserinfoEntities.size() == 0) {
            sysWxuserinfoService.insert(entity);
        }
        return R.ok().put("data",model);
    }

    @RequestMapping("/checkZoneExistByZoneCode/{zoneCode}")
    public R checkZoneExistByZoneCode(@PathVariable("zoneCode") String zoneCode) {
        SysZoneEntity sysZoneEntity = apiService.checkZoneExistByZoneCode(zoneCode);
        return R.ok().put("data",sysZoneEntity);
    }

    @RequestMapping("/insertZoneDetail")
    public R insertZoneDetail(@RequestBody SysZoneEntity entity) {
        entity.setCreatedate(new Date());
        int result = apiService.insertZoneDetail(entity);
        return R.ok().put("result",result);
    }

    @ApiOperation("根据code删除")
    @RequestMapping(value = "/deleteTitleByDelCodeAndID",method = RequestMethod.POST)
    public R deleteTitleByDelCodeAndID(@RequestBody Map<String,Object> params) {
        SysTitleEntity titleDetailByDelCodeAndID = apiService.getTitleDetailByDelCodeAndID(params);
        if (titleDetailByDelCodeAndID == null) {
            return R.ok().put("message","删除码不正确");
        } else {
            int result = apiService.deleteTitleByID(params);
            return R.ok().put("message","删除成功");
        }
    }

    @ApiOperation("评论点赞")
    @PostMapping("/updateGreatCountByCommentID")
    public R updateGreatCountByCommentID(@RequestBody Map<String,Object> params) {
        int result = apiService.updateGreatCountByCommentID(params);
        return R.ok().put("result", result);
    }

    @ApiOperation("文章点赞")
    @PostMapping("/updateLikeCountByTitleID")
    public R updateLikeCountByTitleID(@RequestBody Map<String,Object> params) {
        int result = apiService.updateLikeCountByTitleID(params);
        return R.ok().put("result", result);
    }

    @ApiOperation("文章踩")
    @PostMapping("/updateNotLikeCountByTitleID")
    public R updateNotLikeCountByTitleID(@RequestBody Map<String,Object> params) {
        int result = apiService.updatenotLikeCountByTitleID(params);
        return R.ok().put("result", result);
    }

    @ApiOperation("删除或者举报文章")
    @PostMapping("/delOrReportTitle")
    public R delOrReportTitle(@RequestBody Map<String, Object> params) {
        int type = Integer.valueOf(params.get("type").toString());
        Long ID = Long.valueOf(params.get("titleID").toString());

        if (type == 1) {
            Integer delCode = Integer.valueOf(params.get("inputContent").toString());
            SysTitleEntity entity = new SysTitleEntity();
            entity.setId(ID);
            entity.setDelCode(delCode);
            SysTitleEntity sysTitleEntity = apiService.getSysTitleEntity(entity);
            if (sysTitleEntity != null) {
                sysTitleEntity.setDelstatus(1);
                apiService.updateSysTitleEntity(sysTitleEntity);
                return R.ok().put("message", "删除成功").put("result",1);
            } else {
                return R.ok().put("message", "删除码错误").put("result",2);
            }
        } else {
            return R.ok().put("message","举报成功").put("result",3);
        }
    }

    @ApiOperation("插入评论")
    @PostMapping("/insertSysComment")
    public R insertSysComment(@RequestBody SysCommentEntity entity){
        entity.setCreatedate(new Date());
        int result = apiService.insertSysCommentEntity(entity);
        return R.ok().put("result",result);
    }

    @RequestMapping("/createWXQrCode")
    @ResponseBody
    public R createWxQrCode() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wxcb506c516f5ee36d");
        param.put("secret", "0b81a9888f3972585ecc837d8a950324");
        param.put("grant_type", "client_credential");
        String wxResult = HttpClientUtil.doGet(url, param);
        org.json.JSONObject jsonObject = new org.json.JSONObject(wxResult);
        String access_token = jsonObject.getString("access_token");
        OSSUtil ossUtil = new OSSUtil();
        try {
            ossUtil.asdasd(access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int expires_in = jsonObject.getInt("expires_in");
        redisUtils.set("access_token",access_token,expires_in);
        System.out.println(wxResult);
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + "";
        return R.ok();
    }

    public static void main(String[] args) {
        String urlForToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa6c1aeeb2e756c68&secret=6fbc52179cb10926dffbc44ceee049b9";
        String tokenModelStr = HttpClientUtil.doGet(urlForToken);
        TokenModel tokenModel = JsonUtils.jsonToPojo(tokenModelStr, TokenModel.class);
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser","ofAvj5H8KaNIkNdjQFTPaDqq-qKc");
        jsonObject.put("template_id","XHKcRD4FT2WlymPsFluNFBBVYCkZGrcWlCiV2IQFMLQ");
        jsonObject.put("page","pages/suggestion/index");
        jsonObject.put("form_id","a8c4b419888270da8791a0707c98c88e");
        String data = "{\"keyword1\":{\"value\":\"今日课程提醒\"},\"keyword2\":{\"value\":\"系统显示您今日有课程，点击查看详情\"}}";

        jsonObject.put("data",data);
        jsonObject.put("emphasis_keyword", "keyword1.DATA");
//        JSONObject data2 = new JSONObject(reqbody);
//        JSONObject data = JSON.parseObject(data2.get("data"));
//        //把每个详细数据转化成JSON,fisrt,keyword1,keyword2....,remark
//        Set<String> keySet = reqbody.keySet();
//        for(String key:keySet)
//        {
//            //吧具体数据转化成JSON然后重新放回去
//            if (key.equals("data")) {
//                HashMap<String,Object> data1 = (HashMap<String, Object>) reqbody.get("data");
//                Set<String> set = data1.keySet();
//                for(String key2:set) {
//                    String keyvalue = data1.get(key2).toString();
//                    data.put(key2, keyvalue);
//                }
//
//            }
//        }
//        jsonObject.put("data", data);
        //最后整体数据进行转化为JSON格式用于传递给微信使用
        String argsJSONStr = JSON.toJSONString(jsonObject);
        String templateUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + tokenModel.getAccess_token();
        params.put("data",argsJSONStr);
        System.out.println(argsJSONStr.replaceAll("=",":"));
        System.out.println(argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        String sendTemplateResult = HttpClientUtil.doPostJson(templateUrl,argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        System.out.println(sendTemplateResult);
    }

    @GetMapping("/getConfig")
    public R getConfig() {
        ConfigInfo configInfo = new ConfigInfo();
        List<String> urls = configInfo.getUrls();
        return R.ok().put("data",urls);
    }


    @GetMapping("/queryTakePartInActivityByOpenid")
    @ResponseBody
    @ApiOperation(value = "个人参加的活动列表",notes = "微信openid必须传入，返回数据为list，null时返回空数组")
    public R queryTakePartInActivityByOpenid(@ApiParam(value = "微信openid", required = true, defaultValue = "")@RequestParam(value = "openid",required = true,defaultValue = "123456") String openid) {
        List<ActivityAndSignUpInfoModel> activityAndSignUpInfoModels = apiService.queryActivityAndSignUpInfo(openid);
        return R.ok().put("data",activityAndSignUpInfoModels);
    }
}
