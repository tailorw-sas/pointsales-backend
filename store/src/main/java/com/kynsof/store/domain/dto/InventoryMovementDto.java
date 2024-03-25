package com.kynsof.store.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementDto {
    private UUID id;
    private UUID productId;
    private LocalDateTime movementDate;
    private Integer quantity;
    private MovementType movementType;
    private String description;
    private ProductEntityDto product;
}
