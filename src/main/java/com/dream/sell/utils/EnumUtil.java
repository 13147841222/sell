package com.dream.sell.utils;

import com.dream.sell.enums.CodeEnum;

/**
 * @author zhumingli
 * @create 2018-08-03 下午11:10
 * @desc 通过code 查询 描述
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getDscpByCode(Integer code, Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
