package com.dream.sell.controller;

import com.dream.sell.servise.OrderService;
import com.dream.sell.dto.OrderDTO;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zhumingli
 * @create 2018-08-03 下午10:01
 * @desc
 **/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<OrderDTO> orderDTOPage = orderService.findAllList(pageRequest);

        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO ;
        try{
            orderDTO = orderService.findOne(orderId);
            orderService.cancle(orderDTO);

        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{},orderId={}",e,orderId);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg",ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            map.put("orderDTO",orderDTO);
            return new ModelAndView("order/detail");

        }catch (SellException e){
            log.error("【卖家端订单查询】发生异常{},orderId={}",e,orderId);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){

        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            map.put("msg",ResultEnum.SUCCESS.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/success");

        }catch (SellException e){
            log.error("【卖家端订单完结】发生异常{},orderId={}",e,orderId);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
    }
}
