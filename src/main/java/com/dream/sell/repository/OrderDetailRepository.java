package com.dream.sell.repository;

import com.dream.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhumingli
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * @param oderId
     * @return
     */
    List<OrderDetail> findByOrderId(String oderId);

}
