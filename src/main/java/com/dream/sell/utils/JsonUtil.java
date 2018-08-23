package com.dream.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author zhumingli
 * @create 2018-08-02 下午5:07
 * @desc JSON格式化
 **/
public class JsonUtil {

    /**
     * @param object
     * @return
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = new Gson();
        return gson.toJson(object);
    }


}
