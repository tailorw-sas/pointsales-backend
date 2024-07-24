package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.infrastructure.enumDto.OrderDetailStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderDetailStatus status;

    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public OrderDetail(OrderDetailDto orderDetailDto) {
        this.id = orderDetailDto.getId();
        this.product = new Product(orderDetailDto.getProduct());
        this.quantity = orderDetailDto.getQuantity();
        this.price = orderDetailDto.getPrice();
        this.status = orderDetailDto.getStatus();
    }

    public OrderDetailDto toAggregate() {
        return new OrderDetailDto(this.id,this.product.getId(),this.product.toAggregate(), this.quantity, this.price, this.status);
    }
}
