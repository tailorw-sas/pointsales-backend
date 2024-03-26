package com.kynsof.store.application.query.inventory.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.store.application.query.inventory.getAll.InventoryResponse;
import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.services.IInventoryMovementService;
import org.springframework.stereotype.Component;

@Component
public class FindInventoryByIdQueryHandler implements IQueryHandler<FindInventoryByIdQuery, InventoryResponse> {

    private final IInventoryMovementService movementService;


    public FindInventoryByIdQueryHandler(IInventoryMovementService categoryService) {
        this.movementService = categoryService;
    }

    @Override
    public InventoryResponse handle(FindInventoryByIdQuery query) {
        InventoryMovementDto movementServiceById = movementService.findById(query.getId());
        return new InventoryResponse(movementServiceById);
    }
}

