package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.subcategory.SubcategoryRequest;
import com.kynsof.store.application.command.subcategory.create.CreateSubcategoryCommand;
import com.kynsof.store.application.command.subcategory.create.CreateSubcategoryMessage;
import com.kynsof.store.application.command.subcategory.delete.DeleteSubCategoryMessage;
import com.kynsof.store.application.command.subcategory.delete.DeleteSubcategoryCommand;
import com.kynsof.store.application.command.subcategory.update.UpdateSubcategoryCommand;
import com.kynsof.store.application.command.subcategory.update.UpdateSubcategoryMessage;
import com.kynsof.store.application.query.subcategory.getAll.GetAllSubcategoriesQuery;
import com.kynsof.store.application.query.subcategory.getAll.SubcategoryResponse;
import com.kynsof.store.application.query.subcategory.getById.FindSubcategoryByIdQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    private final IMediator mediator;

    public SubcategoryController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateSubcategoryMessage> createSubcategory(@RequestBody SubcategoryRequest request) {
        CreateSubcategoryCommand createCommand = CreateSubcategoryCommand.fromFrontRequest(request);
        CreateSubcategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateSubcategoryMessage> updateSubcategory(@PathVariable UUID id, @RequestBody SubcategoryRequest request) {
        UpdateSubcategoryCommand createCommand = UpdateSubcategoryCommand.fromRequest(id,request);
        UpdateSubcategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllSubcategories() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryResponse> getSubcategoryById(@PathVariable UUID id) {
        FindSubcategoryByIdQuery query = new FindSubcategoryByIdQuery(id);
        SubcategoryResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchSubcategories(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetAllSubcategoriesQuery query = new GetAllSubcategoriesQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DeleteSubCategoryMessage> deleteSubcategory(@PathVariable UUID id) {
        DeleteSubcategoryCommand command = new DeleteSubcategoryCommand(id);
        DeleteSubCategoryMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }
}
