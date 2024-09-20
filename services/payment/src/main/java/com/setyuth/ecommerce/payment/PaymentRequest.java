package com.setyuth.ecommerce.payment;

import com.setyuth.ecommerce.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
