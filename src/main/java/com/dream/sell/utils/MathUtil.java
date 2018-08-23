package com.dream.sell.utils;

/**
 * @author zhumingli
 * @create 2018-08-02 下午10:13
 * @desc 金额
 **/
public class MathUtil {

    private static final Double ZOO = 0.01;
    /**
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1, Double d2){
       Double result = Math.abs(d1 - d2);
        if(result < ZOO){
            return true;
        }
        return false;
    }
}
