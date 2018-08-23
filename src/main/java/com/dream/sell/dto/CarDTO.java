package com.dream.sell.dto;

import lombok.Data;

/**
 * @author zhumingli
 * @create 2018-07-25 下午11:19
 * @desc 购物车
 **/
@Data
public class CarDTO {

    private String  productId;

    private Integer productQuantity;

    public CarDTO(String productId,Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
