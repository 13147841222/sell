package com.dream.sell.controller;

import com.dream.sell.VO.ProductInfoVO;
import com.dream.sell.VO.ProductVO;
import com.dream.sell.VO.ResultVO;
import com.dream.sell.dataobject.ProductCategory;
import com.dream.sell.servise.CategoryServise;
import com.dream.sell.utils.ResultVOUtil;
import com.dream.sell.dataobject.ProductInfo;
import com.dream.sell.servise.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhumingli
 */

@RestController
@RequestMapping("/buyer/product")
public class BugerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryServise categoryServise;

    /*
      condition  满足什么条件
      unless  不成立时
     */
    @GetMapping("list")
    @Cacheable(cacheNames = "product", key = "123", unless = "#result.getCode() != 0")
    public ResultVO list(){

        //1 查询所有的上架商品
        List<ProductInfo> productInfoList =  productService.findUpAll();
        //2。查询类目
        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo:productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        //精简做法 lambda
        categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryServise.findByCategoryTypeIn(categoryTypeList);

        //3字段拼接
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());



            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList
                 ) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


//        ResultVO resultVO = new ResultVO();
//        ProductVO productVO = new ProductVO();
//        ProductInfoVO productInfoVO = new ProductInfoVO();

//        resultVO.setCode(0);
//        resultVO.setMeg("成功");

//        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));



//        resultVO.setData(productVOList);
//        return resultVO;

        return ResultVOUtil.success(productVOList);
    }



}
