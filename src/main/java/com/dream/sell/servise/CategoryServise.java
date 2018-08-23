package com.dream.sell.servise;

import com.dream.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author zhumingli
 */
public interface CategoryServise {
    /**
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList );

    /**
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);


}
