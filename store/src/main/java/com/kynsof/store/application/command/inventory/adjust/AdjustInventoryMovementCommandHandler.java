package com.kynsof.store.application.command.inventory.adjust;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.dto.MovementType;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.IInventoryMovementService;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AdjustInventoryMovementCommandHandler implements ICommandHandler<AdjustInventoryMovementCommand> {

    private final IInventoryMovementService movementService;
    private final IProductService productService;

    @Autowired
    public AdjustInventoryMovementCommandHandler(IInventoryMovementService movementService, IProductService productService) {
        this.movementService = movementService;
        this.productService = productService;
    }

    @Override
    public void handle(AdjustInventoryMovementCommand command) {
        ProductEntityDto productEntityDto = productService.findById(command.getProductId());
        UUID adjustmentId = movementService.createAdjustment(
                command.getOriginalMovementId(),
                new InventoryMovementDto(
                        UUID.randomUUID(),
                        command.getProductId(),
                        LocalDateTime.now(),
                        command.getQuantity(),
                        MovementType.ADJUSTMENT,
                        command.getDescription(),
                        productEntityDto
                ));
        command.setAdjustmentId(adjustmentId);
    }
}
