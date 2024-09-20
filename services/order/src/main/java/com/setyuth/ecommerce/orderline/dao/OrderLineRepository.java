package com.setyuth.ecommerce.orderline.dao;

import com.setyuth.ecommerce.orderline.dto.OrderLine;
import com.setyuth.ecommerce.orderline.dto.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
