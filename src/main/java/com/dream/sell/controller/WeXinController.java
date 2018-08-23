package com.dream.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhumingli
 * @create 2018-08-01 下午9:57
 * @desc 微信
 **/
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeXinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code ,@RequestParam("sate") String state){
        log.info("进入auth方法,code={},state={}",code,state);

        String url = "";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);


    }
}
