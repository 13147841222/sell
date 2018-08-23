package com.dream.sell.servise;

/**
 * @author zhumingli
 * @create 2018-08-08 上午11:13
 * @desc
 **/
public interface SecKillService {

    /**
     * 查询秒杀活动特价商品的信息
     *
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 模拟不同用户秒杀同一商品的请求
     *
     * @param productId
     * @return
     */
    void orderProductMockDiffUser(String productId);

}
