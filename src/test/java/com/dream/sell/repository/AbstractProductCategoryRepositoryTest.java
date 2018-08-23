package com.dream.sell.repository;

import com.dream.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractProductCategoryRepositoryTest {

    @Autowired
    private AbstractProductCategoryRepository repository;

    @Test
    public void findOneTest(){
      ProductCategory productCategory =  repository.findOne(1);
        System.out.println(productCategory.toString());

    }

    @Test
    @Transactional
    public void savetTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("zhuml");
        productCategory.setCategoryType(2);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);
    }

    @Test
    public void udapteTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("zhuml");
        productCategory.setCategoryType(5);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(1,2);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}