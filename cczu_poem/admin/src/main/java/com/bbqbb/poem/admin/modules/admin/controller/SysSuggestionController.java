package com.bbqbb.poem.admin.modules.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.bbqbb.poem.common.utils.GeetestLib;
import com.bbqbb.poem.common.utils.PageUtils;
import com.bbqbb.poem.common.utils.R;
import com.bbqbb.poem.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bbqbb.poem.admin.modules.admin.entity.SysSuggestionEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysSuggestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-06 09:38:34
 */
@RestController
@RequestMapping("admin/syssuggestion")
public class SysSuggestionController {
    @Autowired
    private SysSuggestionService sysSuggestionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("admin:syssuggestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysSuggestionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("admin:syssuggestion:info")
    public R info(@PathVariable("id") Long id){
        SysSuggestionEntity sysSuggestion = sysSuggestionService.selectById(id);

        return R.ok().put("sysSuggestion", sysSuggestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin:syssuggestion:save")
    public R save(@RequestBody SysSuggestionEntity sysSuggestion){
        sysSuggestionService.insert(sysSuggestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("admin:syssuggestion:update")
    public R update(@RequestBody SysSuggestionEntity sysSuggestion){
        ValidatorUtils.validateEntity(sysSuggestion);
        sysSuggestionService.updateAllColumnById(sysSuggestion);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("admin:syssuggestion:delete")
    public R delete(@RequestBody Long[] ids){
        sysSuggestionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping(value = "/startCaptcha")
    @ResponseBody
    public void startValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib("48f75d83dcebb944830bd97bc63ba943", "dcca7378d60000cf1fa038267ab051a7",false);
        String resStr = "{}";

        String userid = "test";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();

        PrintWriter out = response.getWriter();
        out.println(resStr);
    }

    @RequestMapping(value = "/ajax_validate")
    @ResponseBody
    public void validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib("48f75d83dcebb944830bd97bc63ba943", "dcca7378d60000cf1fa038267ab051a7",false);
        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }


        if (gtResult == 1) {
            // 验证成功
            PrintWriter out = response.getWriter();
            JSONObject data = new JSONObject();
            try {
                data.put("status", "success");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            out.println(data.toString());
        }
        else {
            // 验证失败
            JSONObject data = new JSONObject();
            try {
                data.put("status", "fail");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.println(data.toString());
        }
    }
}
