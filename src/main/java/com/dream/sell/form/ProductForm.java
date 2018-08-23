package com.dream.sell.form;

import lombok.Data;

import java.math.BigDecimal;

import static com.dream.sell.enums.ProductStatusEnum.UP;

/**
 * @author zhumingli
 * @create 2018-08-04 下午5:31
 * @desc
 **/
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus= UP.getCode();

    private Integer categoryType;
}
