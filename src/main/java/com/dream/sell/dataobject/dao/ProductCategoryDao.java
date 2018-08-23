package com.dream.sell.dataobject.dao;

import com.dream.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhumingli
 * @create 2018-08-07 下午10:46
 * @desc
 **/
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * @param map
     * @return
     */
    public int insertByMap(Map<String, Object> map){
        return productCategoryMapper.insertByMap(map);
    }
}
