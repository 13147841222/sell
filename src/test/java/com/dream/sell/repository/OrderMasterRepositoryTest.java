package com.dream.sell.repository;

import com.dream.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void findAllByOpenId() {
        org.springframework.data.domain.Pageable request = new PageRequest(0,10);

        Page<OrderMaster> orderMasters =  orderMasterRepository.findAllByBuyerOpenid("11",request);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }


    /**
     *
     */
    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("mayc");
        orderMaster.setBuyerPhone("11123");
        orderMaster.setBuyerAddress("aaa");
        orderMaster.setOrderAmount(BigDecimal.valueOf(123.3));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMaster.setBuyerOpenid("11");
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);


    }

}