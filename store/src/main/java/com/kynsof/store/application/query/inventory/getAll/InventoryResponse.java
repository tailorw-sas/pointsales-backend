package com.kynsof.store.application.query.inventory.getAll;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.dto.MovementType;
import com.kynsof.store.domain.dto.ProductEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InventoryResponse implements IResponse {
    private UUID id;
    private UUID productId;
    private LocalDateTime movementDate;
    private Integer quantity;
    private MovementType movementType;
    private String description;
    private ProductEntityDto product;

    public InventoryResponse(InventoryMovementDto categoryDto) {
        this.id = categoryDto.getId();
        this.productId = categoryDto.getProductId();
        this.description = categoryDto.getDescription();
        this.quantity = categoryDto.getQuantity();
        this.movementType = categoryDto.getMovementType();
        this.product = categoryDto.getProduct();
    }
}
