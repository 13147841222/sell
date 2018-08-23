package com.dream.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhumingli
 */

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1138454894315477058L;

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String meg;

    /**
     *
     */
    private T data;
}
