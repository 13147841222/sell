package com.dream.sell.servise.impl;

import com.dream.sell.converter.OrderMasterToOrderDTOConverter;
import com.dream.sell.dataobject.OrderDetail;
import com.dream.sell.dataobject.OrderMaster;
import com.dream.sell.dataobject.ProductInfo;
import com.dream.sell.dto.CarDTO;
import com.dream.sell.dto.OrderDTO;
import com.dream.sell.enums.OrderStatusEnum;
import com.dream.sell.enums.PayStutasEnum;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.ResponseBankException;
import com.dream.sell.exception.SellException;
import com.dream.sell.repository.OrderDetailRepository;
import com.dream.sell.repository.OrderMasterRepository;
import com.dream.sell.servise.*;
import com.dream.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhumingli
 * @create 2018-07-25 下午10:41
 * @desc
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    /**
     *
     */
    String orderid = KeyUtil.getUUID();

    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PushMessage pushMessage;

    @Autowired
    private PayService payService;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
//        计算库存
        List<CarDTO> carDTOList = new ArrayList<>();
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
//                throw new SellException(ResultEnum.PROTECTED_NOT_EXIST);
                throw new ResponseBankException();
            }

            orderAmount = orderAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));

            orderDetail.setDetailId(KeyUtil.getUUID());
            orderDetail.setOrderId(orderid);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);

//        CarDTO carDTO = new CarDTO(productInfo.getProductId(),orderDetail.getProductQuantity());

        }

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderid);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStutasEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        carDTOList = orderDTO.getOrderDetailList().stream().map(e ->
            new CarDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(carDTOList);

        //发送消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList  = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAllByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList =OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements()) ;


        return orderDTOPage;
    }

    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult == null){
            log.error("【取消订单】更新订单失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //加库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情,orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().
                stream().map(e -> new CarDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

        productService.increaseStock(carDTOList);

        if(orderDTO.getPayStatus().equals(PayStutasEnum.SUCCESS.getCode())){
            //TODO
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult == null){
            log.error("【完结订单】更新订单失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //推送微信模版消息
        pushMessage.orderStatus(orderDTO);

        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付完成】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        if(!orderDTO.getPayStatus().equals(PayStutasEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        orderDTO.setPayStatus(PayStutasEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);


        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult == null){
            log.error("【完结订单】更新订单失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAllList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList =OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements()) ;

        return orderDTOPage;
    }
}
