package com.dream.sell.controller;

import com.dream.sell.dataobject.ProductCategory;
import com.dream.sell.servise.CategoryServise;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.SellException;
import com.dream.sell.form.CategoryForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @create 2018-08-04 下午8:31
 * @desc
 **/
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryServise categoryServise;


    /**w
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryServise.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String, Object> map){
        ProductCategory productCategory;
        if(categoryId != null){
            productCategory = categoryServise.findOne(categoryId);
            map.put("productCategory",productCategory);
        }


        return new ModelAndView("category/index",map);
    }


    /**
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error");
        }
        ProductCategory productCategory = new ProductCategory();

        try {
            if(categoryForm.getCategoryId() != null){
                productCategory = categoryServise.findOne(categoryForm.getCategoryId());
            }

            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryServise.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error");
        }

        map.put("url","/sell/seller/category/list");
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }

}
