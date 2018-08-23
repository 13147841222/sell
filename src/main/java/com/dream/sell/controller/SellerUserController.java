package com.dream.sell.controller;

import com.dream.sell.config.ProjectUrlConfig;
import com.dream.sell.constant.CookieConstant;
import com.dream.sell.constant.RedisConstant;
import com.dream.sell.dataobject.SellerInfo;
import com.dream.sell.servise.SellerService;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.utils.CookieUtil;
import com.dream.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * @author zhumingli
 * @create 2018-08-05 下午3:51
 * @desc
 **/

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){
        //1、跟数据库中的匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            log.error("");
            map.put("msg",ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }


        //2、设置token至redis
        String token = KeyUtil.getUUID();
        Integer expire = RedisConstant.EXPIRE;

        /*
         * redis中设置token
         * params1 key
         * params2 value
         * params3 过期时间
         * params4 时间单位
         */
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire,TimeUnit.SECONDS);
        //3、设置token至cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getProjectUrl() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map){
        //1、从cookie里面查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //2、清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3、清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }

        map.put("msg",ResultEnum.LOGOUT_SUCCESS);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("",map);

    }
}
