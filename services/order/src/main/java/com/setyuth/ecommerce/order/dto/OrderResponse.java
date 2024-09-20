package com.setyuth.ecommerce.order.dto;

import com.setyuth.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
