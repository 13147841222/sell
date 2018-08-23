package com.dream.sell.repository;

import com.dream.sell.dataobject.SellerInfo;
import com.dream.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.getUUID());
        sellerInfo.setUsername("zhuml");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("ooYFH6E9ifrnPsdI0_OeYabYnUo8");
        SellerInfo reslut = sellerInfoRepository.save(sellerInfo);
        Assert.assertEquals("zhuml",reslut.getUsername());
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("ooYFH6E9ifrnPsdI0_OeYabYnUo8");
        Assert.assertEquals("ooYFH6E9ifrnPsdI0_OeYabYnUo8",sellerInfo.getOpenid());

    }
}