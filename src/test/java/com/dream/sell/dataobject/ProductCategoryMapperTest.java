package com.dream.sell.dataobject;

import com.dream.sell.dataobject.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap(){
        Map<String ,Object> map = new HashMap<>();
        map.put("category_name","红烧肉");
        map.put("category_type",123);
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("sad");
        productCategory.setCategoryType(111);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = productCategoryMapper.findByCategoryType(111);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByCategorytype(){
        int result = productCategoryMapper.updateByCategorytype("fff",111);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("sad");
        productCategory.setCategoryType(111);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deketeByCategorytype(){
        int result = productCategoryMapper.deleteByCategorytype(111);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategorytype(){
        ProductCategory result = productCategoryMapper.selectByCategorytype(111);
        Assert.assertNotNull(result);
    }
}
