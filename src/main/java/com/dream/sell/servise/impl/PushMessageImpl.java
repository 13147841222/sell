package com.dream.sell.servise.impl;

import com.dream.sell.config.WeChatAccountConfig;
import com.dream.sell.servise.PushMessage;
import com.dream.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhumingli
 * @create 2018-08-06 下午10:33
 * @desc
 **/
@Service
@Slf4j
public class PushMessageImpl implements PushMessage {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setToUser(weChatAccountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setTemplateId(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first","亲记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","13147841222"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5","¥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","谢谢惠顾")
        );

        wxMpTemplateMessage.setData(wxMpTemplateDataList);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败 {}",e);
        }
    }
}
