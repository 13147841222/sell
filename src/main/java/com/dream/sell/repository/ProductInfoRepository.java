package com.dream.sell.repository;


import com.dream.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhumingli
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
