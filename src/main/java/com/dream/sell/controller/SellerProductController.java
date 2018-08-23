package com.dream.sell.controller;

import com.dream.sell.dataobject.ProductCategory;
import com.dream.sell.dataobject.ProductInfo;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.SellException;
import com.dream.sell.form.ProductForm;
import com.dream.sell.servise.CategoryServise;
import com.dream.sell.servise.ProductService;
import com.dream.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author zhumingli
 * @create 2018-08-04 下午2:55
 * @desc 卖家商品列表
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryServise categoryServise;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);

        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam("productId")String productId,
                                Map<String, Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam("productId")String productId,
                                 Map<String, Object> map){

        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success");
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false)String productId,
                      Map<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> productCategoryList = categoryServise.findAll();
        map.put("productCategoryList",productCategoryList);

        return new ModelAndView("product/index",map);
    }

    /**
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product" , key = "123")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error");
        }


        try{
            ProductInfo productInfo = new ProductInfo();
            if(StringUtils.isEmpty(productForm.getProductId())){
                productForm.setProductId(KeyUtil.getUUID());
            }else {
                productInfo = productService.findOne(productForm.getProductId());
            }

            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error");
        }

        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }
}
