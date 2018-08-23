package com.dream.sell.repository;

import com.dream.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhumingli
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String > {


    /**
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findAllByBuyerOpenid(String buyerOpenId, Pageable pageable);


}
