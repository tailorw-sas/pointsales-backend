package com.kynsof.store.infrastructure.entity;

import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.dto.MovementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private LocalDateTime movementDate;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private String description;
    
    @CreationTimestamp
    @Column(nullable = true, updatable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime updatedAt;

    public InventoryMovement(InventoryMovementDto inventoryMovementDto) {
        this.id = inventoryMovementDto.getId();
        this.product = new Product(inventoryMovementDto.getProduct());
        this.movementDate = inventoryMovementDto.getMovementDate();
        this.quantity = inventoryMovementDto.getQuantity();
        this.movementType = inventoryMovementDto.getMovementType();
        this.description = inventoryMovementDto.getDescription();
    }

    public InventoryMovementDto toAggregate(){
        return new InventoryMovementDto(id,product.getId(),movementDate,quantity, movementType,description, product.toAggregate());

    }
}

