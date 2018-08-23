package com.dream.sell.repository;

import com.dream.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList =  orderDetailRepository.findByOrderId("11");

        Assert.assertNotEquals(0,orderDetailList.size());
    }

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("11");
        orderDetail.setProductIcon("http://");
        orderDetail.setProductName("zhuml");
        orderDetail.setProductId("222");
        orderDetail.setProductPrice(new BigDecimal(123));
        orderDetail.setProductQuantity(1);

        OrderDetail result =  orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(result);
    }
}