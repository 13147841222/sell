package com.dream.sell.enums;

import lombok.Getter;

/**
 * @author zhumingli
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    /**
     *
     */
    NEW(0,"新订单"),
    /**
     *
     */
    FINISHED(1,"完成"),
    /**
     *
     */
    CANCEL(2,"取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    //笨写法
    public static OrderStatusEnum getOrderStatusEnum(Integer code){
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if(orderStatusEnum.getCode().equals(code)){
                return orderStatusEnum;
            }
        }
        return null;
    }
}
