package com.dream.sell.servise.impl;/*
 * zhumingli
 * 2018/7/21
 *
 */

import com.dream.sell.dataobject.ProductCategory;
import com.dream.sell.repository.AbstractProductCategoryRepository;
import com.dream.sell.servise.CategoryServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhumingli
 */
@Service
public class CategoryServiseImpl implements CategoryServise {

    @Autowired
    private AbstractProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
