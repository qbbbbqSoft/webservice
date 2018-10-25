package com.cczu.spider.utils;

import com.cczu.spider.pojo.CoursePojo;

import java.util.List;

public class JsonArrayUtil {
    public StringBuilder jsonArratUtil(String[][] strings){
        StringBuilder result = new StringBuilder();
        StringBuilder stringBuilder;
        int length = strings.length;
        for (int i = 0; i < length; i++) {
            stringBuilder = new StringBuilder();
            if (i == 0) {
                result.append("[");
            }
            int len = strings[i].length;
            for (int j = 0; j < len; j++) {
                if (j == 0) {
                    stringBuilder.append("[");
                    stringBuilder.append(strings[i][j]);
                } else {
                    stringBuilder.append("\"");
                    stringBuilder.append(strings[i][j]);
                    stringBuilder.append("\"");
                }
                if (j != len - 1) {
                    stringBuilder.append(",");
                }
                if (i != length - 1 && j == len - 1) {
                    stringBuilder.append("],");
                }
                if (i == length - 1 &&j == len - 1) {
                    stringBuilder.append("]");
                }
            }
            result.append(stringBuilder);
            if (i == length - 1 ) {
                result.append("]");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] strings = new String[][]{{"1","2","3"},{"1","2","3"},{"1","2","3"},{"1","2","3"}};
//        jsonArratUtil(strings);
    }

//    public List<CoursePojo> listCourse(String[][] strings) {
//
//    }
}
