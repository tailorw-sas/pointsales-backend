package com.kynsof.store.application.command.inventory.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.store.domain.dto.MovementType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateInventoryMovementCommand implements ICommand {
    private UUID id;
    private final UUID productId;
    private final Integer quantity;
    private final MovementType movementType;
    private final String description;

    public CreateInventoryMovementCommand(UUID productId, Integer quantity, MovementType movementType, String description) {

        this.productId = productId;
        this.quantity = quantity;
        this.movementType = movementType;
        this.description = description;
    }
    public static CreateInventoryMovementCommand fromRequest(InventoryMovementRequest request) {
        return new CreateInventoryMovementCommand(
                request.getProductId(),
                request.getQuantity(),
                request.getMovementType(),
                request.getDescription()
        );
    }

    @Override
    public ICommandMessage getMessage() {

        return new CreateInventoryMovementMessage(id);
    }
}
