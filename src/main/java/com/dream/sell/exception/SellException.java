package com.dream.sell.exception;

import com.dream.sell.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @author zhumingli
 * @create 2018-07-25 下午10:50
 * @desc 异常
 **/
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    private String message;

    /**
     * @param resultEnum
     */
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code , String message){
        this.code = code;
        this.message = message;
    }
}
