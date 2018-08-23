package com.dream.sell.servise.impl;

import com.dream.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiseImplTest {

    @Autowired
    private CategoryServiseImpl categoryServise;

    @Test
    public void findOne() {
        AtomicReference<ProductCategory> productCategory = new AtomicReference<>(categoryServise.findOne(1));
        Assert.assertEquals(new Integer(1), productCategory.get().getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryServise.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryServise.findByCategoryTypeIn(Arrays.asList(1,2));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("lalal");
        ProductCategory result = categoryServise.save(productCategory);

        Assert.assertNotNull(result);
    }
}