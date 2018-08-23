package com.dream.sell.utils;

import java.util.Random;

/**
 * @author zhumingli
 * @create 2018-07-25 下午11:06
 * @desc uuid
 * 多线程时有可能重复 所以使用 synchronized
 **/
public class KeyUtil {
    public static synchronized String getUUID(){
        Random random = new Random();

        Integer id = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(id);
    }
}
