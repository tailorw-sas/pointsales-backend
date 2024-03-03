package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.OrderEntityDto;
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

    public OrderEntityDto.OrderDetailDto toAggregate() {
        return new OrderEntityDto.OrderDetailDto(this.product.getId(), this.quantity, this.price);
    }
}
