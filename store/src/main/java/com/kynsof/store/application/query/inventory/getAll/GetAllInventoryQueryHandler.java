package com.kynsof.store.application.query.inventory.getAll;


import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.store.domain.services.IInventoryMovementService;
import org.springframework.stereotype.Component;

@Component
public class GetAllInventoryQueryHandler implements IQueryHandler<GetAlInventoryQuery, PaginatedResponse> {

    private final IInventoryMovementService movementService;

    public GetAllInventoryQueryHandler(IInventoryMovementService categoryService) {
        this.movementService = categoryService;
    }

    @Override
    public PaginatedResponse handle(GetAlInventoryQuery query) {
        return movementService.search(query.getPageable(), query.getFilter());
    }
}
