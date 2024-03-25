package com.kynsof.store.domain.services;

import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IInventoryMovementService {

    /**
     * Creates a new inventory movement record.
     *
     * @param inventoryMovementDto the data transfer object containing the information for the new inventory movement
     * @return the UUID of the newly created inventory movement record
     */
    UUID create(InventoryMovementDto inventoryMovementDto);
    UUID createAdjustment(UUID originalMovementId, InventoryMovementDto adjustmentDto);
    /**
     * Finds an inventory movement by its ID.
     *
     * @param id the UUID of the inventory movement to find
     * @return an InventoryMovementDto representing the found inventory movement
     */
    InventoryMovementDto findById(UUID id);

    /**
     * Lists all inventory movements, with optional pagination.
     *
     * @param pageable the Pageable object specifying the pagination parameters (optional)
     * @return a PaginatedResponse containing the list of inventory movements
     */
    PaginatedResponse findAll(Pageable pageable);

    /**
     * Searches through inventory movements based on certain criteria, with optional pagination.
     *
     * @param pageable the Pageable object specifying the pagination parameters (optional)
     * @param filterCriteria a list of FilterCriteria specifying the search criteria
     * @return a PaginatedResponse containing the list of inventory movements that match the search criteria
     */
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
