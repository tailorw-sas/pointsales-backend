package com.kynsof.store.application.command.inventory.adjust;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.application.command.inventory.create.InventoryMovementRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AdjustInventoryMovementCommand implements ICommand {
    private UUID adjustmentId; // Este ID se establecerá después de crear el ajuste
    private final UUID originalMovementId;
    private final UUID productId;
    private final Integer quantity; // Puede ser positivo o negativo dependiendo del ajuste
    private final String description;

    public AdjustInventoryMovementCommand(UUID originalMovementId, UUID productId, Integer quantity, String description) {
        this.originalMovementId = originalMovementId;
        this.productId = productId;
        this.quantity = quantity;
        this.description = description;
    }

    public static AdjustInventoryMovementCommand fromRequest(UUID adjustmentId,InventoryMovementRequest request) {
        return new AdjustInventoryMovementCommand(
                adjustmentId,
                request.getProductId(),
                request.getQuantity(),
                request.getDescription()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
