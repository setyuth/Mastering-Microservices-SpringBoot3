package com.setyuth.ecommerce.service;

import com.setyuth.ecommerce.customer.CustomerClient;
import com.setyuth.ecommerce.exception.BusinessException;
import com.setyuth.ecommerce.kafka.OrderConfirmation;
import com.setyuth.ecommerce.kafka.OrderProducer;
import com.setyuth.ecommerce.order.dao.OrderRepository;
import com.setyuth.ecommerce.order.dto.OrderRequest;
import com.setyuth.ecommerce.order.dto.OrderResponse;
import com.setyuth.ecommerce.orderline.OrderLineRequest;
import com.setyuth.ecommerce.orderline.OrderLineService;
import com.setyuth.ecommerce.payment.PaymentClient;
import com.setyuth.ecommerce.payment.PaymentRequest;
import com.setyuth.ecommerce.product.ProductClient;
import com.setyuth.ecommerce.product.dto.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public Integer createdOrder(OrderRequest request) {

        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        // purchase the products --> use product-ms
        var purchaseProducts = this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.orderRepository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with ID %d", orderId)));
    }
}
