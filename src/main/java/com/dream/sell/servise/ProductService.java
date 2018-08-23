package com.dream.sell.servise;

import com.dream.sell.dto.CarDTO;
import com.dream.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhumingli
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);


    /**
     * @param carDTOList
     */
    void increaseStock(List<CarDTO> carDTOList);


    /**
     * @param carDTOList
     */
    void decreaseStock(List<CarDTO> carDTOList);

    /**
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

    /**
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

}
