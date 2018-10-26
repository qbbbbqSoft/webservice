package com.cczu.spider.repository;

import com.cczu.spider.entity.SysSuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface SysSuggestionRepo extends JpaRepository<SysSuggestionEntity,Long> {

    List<SysSuggestionEntity> getSuggestionBySearch(Map<String,Object> params);

}
