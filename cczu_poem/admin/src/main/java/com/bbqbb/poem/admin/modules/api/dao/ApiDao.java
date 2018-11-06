package com.bbqbb.poem.admin.modules.api.dao;

import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApiDao {

    List<SysTitleModel> getTitleList();
}
