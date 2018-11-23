package com.cczu.spider.utils.thirdpart;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherUtil {
    public String getWeather() throws Exception{
//        String appkey = "35436";
//        String sign = "ad8574d8ecc1152f92334f277025ea05";
//        String weaid = "101191104";//武进
//        String http = "https://sapi.k780.com/";
//        String dataJson = "&format=json";
        String url = "http://t.weather.sojson.com/api/weather/city/101191101";
        URL u=new URL(url);
        InputStream in=u.openStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        return new String(b,"utf-8");
    }

    public static void main(String[] args) {
        Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        List<Integer> week = Arrays.asList(new Integer[]{7, 1, 2, 3, 4, 5, 6});
        System.out.println(week.get(weekday - 1));
    }

}
