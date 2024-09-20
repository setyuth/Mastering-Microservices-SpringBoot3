package com.setyuth.ecommerce.orderline.mapper;

import com.setyuth.ecommerce.order.dto.Order;
import com.setyuth.ecommerce.orderline.OrderLineRequest;
import com.setyuth.ecommerce.orderline.dto.OrderLine;
import com.setyuth.ecommerce.orderline.dto.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.id())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
