package com.setyuth.ecommerce.orderline.dto;

import com.setyuth.ecommerce.order.dto.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer_line")
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;
}
