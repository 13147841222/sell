package com.dream.sell.repository;

import com.dream.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhumingli
 * @create 2018-08-05 下午2:09
 * @desc
 **/
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    /**
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);


}