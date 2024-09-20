package com.setyuth.ecommerce.order.dao;

import com.setyuth.ecommerce.order.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
