package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.OrderDetailDto;
import com.kynsof.store.domain.dto.OrderEntityDto;
import com.kynsof.store.infrastructure.enumDto.OrderStatus;
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

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") // Esta columna en la tabla Order enlaza a Customer
    private Customer customer;
    public Order(OrderEntityDto orderDto) {
        this.id = orderDto.getId();
        this.orderDate = orderDto.getOrderDate();
        this.status = orderDto.getStatus();
        this.customer = new Customer(orderDto.getCustomerDto());
       this.orderDetails = orderDto.getOrderDetails().stream()
                    .map(orderDetailDto -> {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setProduct(new Product(orderDetailDto.getProduct()));
                        orderDetail.setPrice(orderDetailDto.getPrice());
                        orderDetail.setQuantity(orderDetailDto.getQuantity());
                        orderDetail.setStatus(orderDetailDto.getStatus());
                        orderDetail.setOrder(this);
                        return orderDetail;
                    })
                    .collect(Collectors.toList());
    }

    public OrderEntityDto toAggregate() {
        List<OrderDetailDto> orderDetailsDto = this.orderDetails.stream()
                .map(OrderDetail::toAggregate)
                .collect(Collectors.toList());
        return new OrderEntityDto(this.id,  this.orderDate, this.status, orderDetailsDto, this.customer.getId(),this.customer.toAggregate());
    }
}
