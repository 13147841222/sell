package com.dream.sell.dto;

import com.dream.sell.dataobject.OrderDetail;
import com.dream.sell.enums.OrderStatusEnum;
import com.dream.sell.enums.PayStutasEnum;
import com.dream.sell.utils.EnumUtil;
import com.dream.sell.utils.serializer.DataToLongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhumingli
 * @create 2018-07-25 下午7:22
 * @desc
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus ;

    private Integer payStatus ;

    @JsonSerialize(using = DataToLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DataToLongSerializer.class)
    private Date updateTime;


    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getDscpByCode(this.orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStutasEnum getPayStutasEnum(){
        return EnumUtil.getDscpByCode(this.payStatus,PayStutasEnum.class);
    }

}
