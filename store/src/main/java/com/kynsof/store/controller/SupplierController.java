package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.supplier.create.CreateSupplierCommand;
import com.kynsof.store.application.command.supplier.create.CreateSupplierMessage;
import com.kynsof.store.application.command.supplier.create.SupplierRequest;
import com.kynsof.store.application.command.supplier.delete.DeleteSupplierCommand;
import com.kynsof.store.application.command.supplier.delete.DeleteSupplierMessage;
import com.kynsof.store.application.command.supplier.update.UpdateSupplierCommand;
import com.kynsof.store.application.command.supplier.update.UpdateSupplierMessage;
import com.kynsof.store.application.query.supplier.getAll.GetAllSuppliersQuery;
import com.kynsof.store.application.query.supplier.getAll.SupplierResponse;
import com.kynsof.store.application.query.supplier.getById.FindSupplierByIdQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final IMediator mediator;

    public SupplierController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateSupplierMessage> createSupplier(@RequestBody SupplierRequest request) {
        CreateSupplierCommand createCommand = CreateSupplierCommand.fromFrontRequest(request);
        CreateSupplierMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateSupplierMessage> updateSupplier(@PathVariable UUID id, @RequestBody SupplierRequest request) {
        UpdateSupplierCommand createCommand = UpdateSupplierCommand.fromRequest(id, request);
        UpdateSupplierMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllSuppliers() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable UUID id) {
        FindSupplierByIdQuery query = new FindSupplierByIdQuery(id);
        SupplierResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchSuppliers(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetAllSuppliersQuery query = new GetAllSuppliersQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DeleteSupplierMessage> deleteSupplier(@PathVariable UUID id) {
        DeleteSupplierCommand command = new DeleteSupplierCommand(id);
        DeleteSupplierMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
