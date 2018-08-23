package com.dream.sell.servise;

import com.dream.sell.dataobject.SellerInfo;

/**
 * @author zhumingli
 * @create 2018-08-05 下午3:17
 * @desc
 **/
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
