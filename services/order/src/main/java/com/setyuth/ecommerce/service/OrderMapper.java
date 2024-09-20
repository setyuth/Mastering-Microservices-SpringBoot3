package com.setyuth.ecommerce.service;

import com.setyuth.ecommerce.order.dto.Order;
import com.setyuth.ecommerce.order.dto.OrderRequest;
import com.setyuth.ecommerce.order.dto.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
