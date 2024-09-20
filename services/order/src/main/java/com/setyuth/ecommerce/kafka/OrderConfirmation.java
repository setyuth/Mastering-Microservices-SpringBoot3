package com.setyuth.ecommerce.kafka;

import com.setyuth.ecommerce.customer.dto.CustomerResponse;
import com.setyuth.ecommerce.payment.PaymentMethod;
import com.setyuth.ecommerce.product.dto.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
        ) {
}
