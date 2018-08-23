package com.dream.sell.repository;

import com.dream.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhumingli
 */
public interface  AbstractProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
