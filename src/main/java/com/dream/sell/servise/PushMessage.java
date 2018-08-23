package com.dream.sell.servise;

import com.dream.sell.dto.OrderDTO;

/**
 * @author zhumingli
 * @create 2018-08-06 下午10:31
 * @desc 推送消息
 **/
public interface PushMessage {

    /**
     * 订单
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
