package com.dream.sell.servise;

import com.dream.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @author zhumingli
 * @create 2018-08-02 下午4:15
 * @desc
 **/
public interface PayService {

    /**
     * @param orderDTO
     */
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
