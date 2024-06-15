package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.inventory.adjust.AdjustInventoryMovementCommand;
import com.kynsof.store.application.command.inventory.create.CreateInventoryMovementCommand;
import com.kynsof.store.application.command.inventory.create.InventoryMovementRequest;
import com.kynsof.store.application.query.inventory.getAll.GetAlInventoryQuery;
import com.kynsof.store.application.query.inventory.getAll.InventoryResponse;
import com.kynsof.store.application.query.inventory.getById.FindInventoryByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventoryMovements")
public class InventoryMovementController {

    private final IMediator mediator;

    @Autowired
    public InventoryMovementController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> createInventoryMovement(@RequestBody InventoryMovementRequest request) {
        CreateInventoryMovementCommand command = CreateInventoryMovementCommand.fromRequest(request);
        mediator.send(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/adjust/{originalMovementId}")
    public ResponseEntity<?> adjustInventoryMovement(@PathVariable UUID originalMovementId, @RequestBody InventoryMovementRequest request) {
        AdjustInventoryMovementCommand command =  AdjustInventoryMovementCommand.fromRequest(originalMovementId, request);
        mediator.send(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable UUID id) {
        FindInventoryByIdQuery query = new FindInventoryByIdQuery(id);
        InventoryResponse categoryResponse = mediator.send(query);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchInventory(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetAlInventoryQuery query = new GetAlInventoryQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }
}
