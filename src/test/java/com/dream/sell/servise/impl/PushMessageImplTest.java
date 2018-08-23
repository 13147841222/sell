package com.dream.sell.servise.impl;

import com.dream.sell.servise.OrderService;
import com.dream.sell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageImplTest {

    private static final String ORDER_ID = "1532876498403780112";



    @Autowired
    private PushMessageImpl pushMessage;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus(){
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        pushMessage.orderStatus(orderDTO);
    }

}