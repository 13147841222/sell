package com.dream.sell.dataobject;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;

/**
 * @author zhumingli
 * @create 2018-08-05 下午2:01
 * @desc
 **/
@Data
@Entity
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

}
