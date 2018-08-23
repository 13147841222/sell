package com.dream.sell.controller;

import com.dream.sell.VO.ResultVO;
import com.dream.sell.converter.OrderFormToOrderDTOConverter;
import com.dream.sell.servise.BuyerService;
import com.dream.sell.servise.OrderService;
import com.dream.sell.dto.OrderDTO;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.SellException;
import com.dream.sell.form.OrderForm;
import com.dream.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhumingli
 * @create 2018-07-29 下午2:08
 * @desc
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("【订单创建】参数不正确,orderForm={]",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderDTOResult =  orderService.create(orderDTO);

        Map<String , String > map = new HashMap<>();

        map.put("orderId",orderDTOResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 订单列表
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page",defaultValue = "0") Integer page ,
                         @RequestParam(value = "size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Pageable request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);


        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        //TODO
        OrderDTO orderDTO =  buyerService.findOrderOne(openid,orderId);

        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        buyerService.cancelOrderOne(openid, orderId);

        return ResultVOUtil.success();
    }

}
