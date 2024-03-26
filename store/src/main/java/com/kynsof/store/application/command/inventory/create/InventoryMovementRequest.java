package com.kynsof.store.application.command.inventory.create;

import com.kynsof.store.domain.dto.MovementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementRequest {
    private UUID productId;
    private Integer quantity;
    private MovementType movementType;
    private String description;
}
