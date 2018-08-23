package com.dream.sell.servise.impl;

import com.dream.sell.dataobject.SellerInfo;
import com.dream.sell.repository.SellerInfoRepository;
import com.dream.sell.servise.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhumingli
 * @create 2018-08-05 下午3:17
 * @desc
 **/
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return sellerInfoRepository.findByOpenid(openid);
    }
}
