package com.bbqbb.poem.admin.modules.api.controller;


import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import com.bbqbb.poem.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poem/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("getTitleList")
    public R getTitleList() {
        List<SysTitleModel> titleList = apiService.getTitleList();
        return R.ok().put("data",titleList);
    }

    @RequestMapping("/getTitleDetailByID")
    public R getTitleDetailByID() {
        return R.ok().put("data","asdasdasdadasdasdasdads");
    }
}
