package com.dream.sell.constant;

/**
 * @author zhumingli
 * @create 2018-08-05 下午9:35
 * @desc Redis常量
 **/
public interface RedisConstant {


    /**
     * token前缀
     */
    String TOKEN_PREFIX = "token_%s";

    /**
     * token 过期时间
     */
    Integer EXPIRE = 7200;
}
