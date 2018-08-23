package com.dream.sell.enums;

import lombok.Getter;

/**
 * @author zhumingli
 * @create 2018-07-25 下午10:51
 * @desc
 **/
@Getter
public enum ResultEnum {

    SUCCESS(200,"成功"),
    PARAM_ERROR(1,"参数不正确"),

    CART_EMPTY(18,"购物车不能为空"),

    /**
     *
     */
    PROTECTED_NOT_EXIST(10,"商品不存在"),

    /**
     *
     */
    PROTECTED_STOCK_ERROR(5,"库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    ORDER_OWNER_ERROR(18,"该订单不属于客户"),

    WECHAT_MP_ERROR(19,"微信公众账号错误"),

    AMOUNT_ERROR(20,"微信异步校验：金额不一致"),

    PRODUCT_STATUS_ERROR(21,"商品状态错误"),

    LOGIN_FAIL(25,"登陆信息不正确"),

    LOGOUT_SUCCESS(26,"登出成功"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code ,String message){
        this.code = code;
        this.message = message;

    }


}
