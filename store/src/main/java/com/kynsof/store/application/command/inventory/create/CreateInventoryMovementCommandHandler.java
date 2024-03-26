package com.kynsof.store.application.command.inventory.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.dto.MovementType;
import com.kynsof.store.domain.dto.ProductEntityDto;
import com.kynsof.store.domain.services.IInventoryMovementService;
import com.kynsof.store.domain.services.IProductService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateInventoryMovementCommandHandler implements ICommandHandler<CreateInventoryMovementCommand> {

    private final IInventoryMovementService movementService;
    private final IProductService productService;

    public CreateInventoryMovementCommandHandler(IInventoryMovementService categoryService, IProductService productService) {

        this.movementService = categoryService;
        this.productService = productService;
    }

    @Override
    public void handle(CreateInventoryMovementCommand command) {
        ProductEntityDto productEntityDto = productService.findById(command.getProductId());


        UUID id = movementService.create(new InventoryMovementDto(UUID.randomUUID(),
                command.getProductId(),
                LocalDateTime.now(),
                command.getQuantity(),
                command.getMovementType(),
                command.getDescription(),
                productEntityDto));

        if (command.getMovementType().equals(MovementType.OUT)) {
            productEntityDto.setQuantityInStock(productEntityDto.getQuantityInStock() - command.getQuantity());
        } else if (command.getMovementType().equals(MovementType.IN)) {
            productEntityDto.setQuantityInStock(productEntityDto.getQuantityInStock() + command.getQuantity());
        }
        productService.update(productEntityDto);
        command.setId(id);
    }
}
