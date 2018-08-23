package com.dream.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhumingli
 * @create 2018-08-05 下午3:41
 * @desc 项目URL
 **/
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权url
     */
    public String weChatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    public String weChatOpenAuthorize;

    /**
     * 项目URL
     */
    public String projectUrl;
}
