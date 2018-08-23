package com.dream.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author zhumingli
 * @create 2018-08-05 下午3:28
 * @desc
 **/
public class WeChatOpenConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(WxOpenConfigStorage());
        return wxMpService;
    }

    @Bean
    private WxMpConfigStorage WxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
