package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.OrderDetailDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double price; // Precio acordado en el momento del pedido

    public OrderDetail(OrderDetailDto orderDetailDto) {

    }

    public OrderDetailDto toAggregate() {
        return new OrderDetailDto(this.id,this.product.getId(),this.product, this.quantity, this.price);
    }
}
