package com.dream.sell.servise;

import com.dream.sell.dto.OrderDTO;

/**
 * @author zhumingli
 * @create 2018-07-29 下午11:47
 * @desc
 **/
public interface BuyerService {

    /**
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrderOne(String openid,String orderId);


}
