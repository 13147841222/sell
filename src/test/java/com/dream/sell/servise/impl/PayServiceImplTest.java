package com.dream.sell.servise.impl;

import com.dream.sell.servise.OrderService;
import com.dream.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    private final String ORDER_ID = "1532616968078586942";
    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderService orderService;
    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        payService.create(orderDTO);

    }
}