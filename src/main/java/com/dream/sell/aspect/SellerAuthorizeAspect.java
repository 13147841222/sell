package com.dream.sell.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhumingli
 * @create 2018-08-05 下午10:25
 * @desc AOP 拦截
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.dream.sell.controller.Seller*.*(..))" + "&& !execution(public * com.dream.sell.controller.SellerUserController.*(..))")
    public void verify(){

    }

//    @Before("verify()")
//    public void doVerify(){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        //查询cookie
//        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
//        if(cookie == null ){
//            log.warn("【登陆校验】Cooike中查不到token");
//            throw new SellerAuthorizeException();
//        }
//
//        //redis查询
//        String token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
//        if(StringUtils.isEmpty(token)){
//            log.warn("【登陆校验】redis中查不到token");
//            throw new SellerAuthorizeException();
//        }
//
//    }

}
