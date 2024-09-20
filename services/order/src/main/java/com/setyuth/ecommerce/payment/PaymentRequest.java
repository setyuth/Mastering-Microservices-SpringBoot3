package com.setyuth.ecommerce.payment;

import com.setyuth.ecommerce.customer.dto.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
