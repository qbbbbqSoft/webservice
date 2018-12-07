package com.cczu.spider.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HandlerInterceptorConfig implements HandlerInterceptor {
    /**
     * 关键字
     */
    private final static String SqlKeyWords = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
            "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";

    /**
     * 数组类型
     */
    private final static String[] SqlKeyWordArray = SqlKeyWords.split("\\|");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 参数键值对
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (String[] val : parameterMap.values()) {
            boolean valid = sqlValidate(val);
            // 验证通过
            if (!valid) {
                // 发现异常参数访问
                throw new IllegalAccessException("SqlInjectFilter find Illegal request!!!");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
    }

    /**
     * 检查是否包含sql关键字
     *
     * @param strs
     * @return
     */
    protected static boolean sqlValidate(String... strs) {
        if (strs == null || strs.length == 0)
            return true;
        for (String str : strs) {
            if (StringUtils.isBlank(str)) continue;
            //统一转为小写
            str = str.toLowerCase();
            for (int i = 0; i < SqlKeyWordArray.length; i++) {
                if (str.indexOf(SqlKeyWordArray[i]) >= 0) return false;
            }
        }
        return true;
    }
}
