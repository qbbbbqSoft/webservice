package com.bbqbb.poem.admin.modules.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbqbb.poem.admin.common.utils.HttpClientUtil;
import com.bbqbb.poem.admin.common.utils.JsonUtils;
import com.bbqbb.poem.admin.common.utils.Query;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.api.model.DataModel;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.model.TokenModel;
import com.bbqbb.poem.admin.modules.api.model.WXSessionModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import com.bbqbb.poem.common.exception.RRException;
import com.bbqbb.poem.common.utils.R;
import com.bbqbb.poem.common.utils.RedisUtils;
import com.bbqbb.poem.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/poem/api")
@Api(value = "数据保存和获取的接口")
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApiService apiService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("getTitleList")
    public R getTitleList(@RequestBody Map<String,Object> params) {
        Query query = new Query(params);
        List<SysTitleModel> titleList = apiService.getTitleList(query);
        return R.ok().put("data",titleList);
    }

    @RequestMapping("/getTitleAndCommentDetailByTitleID/{ID}")
    public R getTitleDetailByID(@PathVariable("ID")Long ID) {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",ID);
        param.put("titleID",ID);
        SysTitleEntity titleDetailByID = apiService.getTitleDetailByID(param);
        List<SysCommentEntity> commentDetailByTitleID = apiService.getCommentDetailByTitleID(param);
        redisUtils.set("commentDetailByTitleID:" + System.currentTimeMillis(),commentDetailByTitleID,30);
        String redisStr = redisUtils.get("commentDetailByTitleID:");
        System.out.println(redisStr);
        return R.ok().put("titleDetail",titleDetailByID).put("commentDetail",commentDetailByTitleID);
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

        entity.setCreatedate(new Date());
        int result = apiService.insertSysTitleDetail(entity);
        String urlForToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcb506c516f5ee36d&secret=0b81a9888f3972585ecc837d8a950324";
        String tokenModelStr = HttpClientUtil.doGet(urlForToken);
        TokenModel tokenModel = JsonUtils.jsonToPojo(tokenModelStr, TokenModel.class);
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",reqbody.get("openID").toString());
        jsonObject.put("template_id","GRDaIosbrSHKrvGPp5mY1qy8Hs7cQHSyvjYafpZVysA");
        jsonObject.put("page","pages/kf/index");
        jsonObject.put("form_id",reqbody.get("formID").toString());
        jsonObject.put("data",reqbody.get("data").toString());
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

        return R.ok().put("result", result);
    }


    @PostMapping("/wxLogin")
    public R wxLogin(String code) {

        System.out.println("wxlogin - code: " + code);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wxcb506c516f5ee36d");
        param.put("secret", "0b81a9888f3972585ecc837d8a950324");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);
        WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);

        // 存入session到redis
        redisUtils.set("user-redis-session:" + model.getOpenid(),
                model.getSession_key(),
                60 * 5);

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

}
