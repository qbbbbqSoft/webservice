package com.bbqbb.poem.admin.modules.api.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="my")
public class ConfigInfo {
    private List<String> urls = new ArrayList<String>();

    public List<String> getUrls() {
        return this.urls;
    }
}
