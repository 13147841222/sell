package com.dream.sell.servise.impl;

import com.dream.sell.servise.ProductService;
import com.dream.sell.dto.CarDTO;
import com.dream.sell.enums.ProductStatusEnum;
import com.dream.sell.enums.ResultEnum;
import com.dream.sell.exception.SellException;
import com.dream.sell.dataobject.ProductInfo;
import com.dream.sell.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zhumingli
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CarDTO> carDTOList) {
        for (CarDTO cartDTO: carDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PROTECTED_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) {

        for (CarDTO carDTO: carDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(carDTO.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PROTECTED_NOT_EXIST);
            }
            Integer reslut = productInfo.getProductStock() - carDTO.getProductQuantity();
            if(reslut < 0){
                throw new SellException(ResultEnum.PROTECTED_STOCK_ERROR);
            }
            productInfo.setProductStock(reslut);
            productInfoRepository.save(productInfo);
        }

    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PROTECTED_NOT_EXIST);
        };
        if(productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoRepository.save(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PROTECTED_NOT_EXIST);
        };
        if(productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoRepository.save(productInfo);
        return productInfo;
    }
}
