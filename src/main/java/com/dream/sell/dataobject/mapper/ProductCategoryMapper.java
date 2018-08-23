package com.dream.sell.dataobject.mapper;

import com.dream.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @author zhumingli
 * @create 2018-08-07 下午9:58
 * @desc
 **/
public interface ProductCategoryMapper {

    /**
     * @param map
     * @return
     */
    @Insert("insert into product category(category_name,category_type) values (#{category_name, jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map );

    /**
     * @param productCategory
     * @return
     */
    @Insert("insert into product category(category_name,category_type) values (#{categoryName, jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    /**
     * 查询返回结果时 需要通过 注解 定义映射字段
     * @param categoryType
     * @return
     */
    @Select("select * form product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id" , property = "categoryId"  ),
            @Result(column = "category_type" , property = "categoryType"  ),
            @Result(column = "category_name" , property = "categoryName"  )
    })
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 多个参数时 要写注解
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("udpate product_category set category_name = #{categoryName} where category_type = #{categoryType} ")
    int updateByCategorytype(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    /**
     * @param productCategory
     * @return
     */
    @Update("udpate product_category set category_name = #{categoryName} where category_type = #{categoryType} ")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete product_category where category_type = #{categoryType}")
    int deleteByCategorytype(Integer categoryType);

    ProductCategory selectByCategorytype(Integer categoryType);

}
