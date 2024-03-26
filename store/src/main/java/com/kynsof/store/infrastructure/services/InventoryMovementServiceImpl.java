package com.kynsof.store.infrastructure.services;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.store.application.query.inventory.getAll.InventoryResponse;
import com.kynsof.store.domain.dto.InventoryMovementDto;
import com.kynsof.store.domain.services.IInventoryMovementService;
import com.kynsof.store.infrastructure.entity.InventoryMovement;
import com.kynsof.store.infrastructure.entity.Product;
import com.kynsof.store.infrastructure.repositories.command.InventoryWriteDataJPARepository;
import com.kynsof.store.infrastructure.repositories.queries.InventoryReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryMovementServiceImpl implements IInventoryMovementService {

    @Autowired
    private InventoryWriteDataJPARepository writeRepository;

    @Autowired
    private InventoryReadDataJPARepository readRepository;



    @Override
    public UUID create(InventoryMovementDto inventoryMovementDto) {
        InventoryMovement movement = new InventoryMovement(inventoryMovementDto);
        movement = writeRepository.save(movement);
        return movement.getId();
    }

    @Override
    public UUID createAdjustment(UUID originalMovementId, InventoryMovementDto adjustmentDto) {
        InventoryMovement originalMovement = readRepository.findById(originalMovementId)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento de Inventario original no encontrado para el ID: " + originalMovementId));
        InventoryMovement adjustmentMovement = new InventoryMovement(adjustmentDto);
        adjustmentMovement = writeRepository.save(adjustmentMovement);
        return adjustmentMovement.getId();
    }

    @Override
    public InventoryMovementDto findById(UUID id) {
        InventoryMovement movement = readRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory Movement not found for ID: " + id));
        return movement.toAggregate();
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        return null;
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Product> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<InventoryMovement> data = this.readRepository.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<InventoryMovement> data) {
        List<InventoryResponse> patients = new ArrayList<>();
        for (InventoryMovement p : data.getContent()) {
            patients.add(new InventoryResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
