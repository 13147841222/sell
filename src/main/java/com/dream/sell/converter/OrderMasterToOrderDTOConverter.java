package com.dream.sell.converter;

import com.dream.sell.dataobject.OrderMaster;
import com.dream.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhumingli
 * @create 2018-07-26 下午11:31
 * @desc 转换器
 **/
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->
            convert(e)
        ).collect(Collectors.toList());
    }
}
