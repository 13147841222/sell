package com.dream.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author zhumingli
 */
@Getter
public enum PayStutasEnum implements CodeEnum{


    /**
     *
     */
    WAIT(0,"未支付"),
    /**
     *
     */
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;

    /**
     *
     */
    private String message;

    /**
     * @param code
     * @param message
     */
    PayStutasEnum(Integer code,String message){
        this.message = message;
        this.code = code;
    }
}
