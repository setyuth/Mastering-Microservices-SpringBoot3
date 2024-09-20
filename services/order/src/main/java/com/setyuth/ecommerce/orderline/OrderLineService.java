package com.setyuth.ecommerce.orderline;

import com.setyuth.ecommerce.orderline.dao.OrderLineRepository;
import com.setyuth.ecommerce.orderline.dto.OrderLineResponse;
import com.setyuth.ecommerce.orderline.mapper.OrderLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
