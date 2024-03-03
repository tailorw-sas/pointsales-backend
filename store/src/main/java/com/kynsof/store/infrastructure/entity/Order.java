package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.OrderEntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private LocalDateTime orderDate;
    private String status; // Ejemplo: "PENDING", "COMPLETED", "CANCELLED"

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public OrderEntityDto toAggregate() {
        List<OrderEntityDto.OrderDetailDto> orderDetailsDto = this.orderDetails.stream()
                .map(OrderDetail::toAggregate)
                .collect(Collectors.toList());
        return new OrderEntityDto(this.id, this.supplier.getId(), this.orderDate, this.status, orderDetailsDto);
    }
}
