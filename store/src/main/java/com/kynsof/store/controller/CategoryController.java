package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.category.CategoryRequest;
import com.kynsof.store.application.command.category.create.CreateCategoryCommand;
import com.kynsof.store.application.command.category.create.CreateCategoryMessage;
import com.kynsof.store.application.command.category.delete.DeleteCategoryCommand;
import com.kynsof.store.application.command.category.delete.DeleteCategoryMessage;
import com.kynsof.store.application.command.category.update.UpdateCategoryCommand;
import com.kynsof.store.application.command.category.update.UpdateCategoryMessage;
import com.kynsof.store.application.query.category.getAll.CategoryResponse;
import com.kynsof.store.application.query.category.getAll.GetAllCategoriesQuery;
import com.kynsof.store.application.query.category.getById.FindCategoryByIdQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final IMediator mediator;

    public CategoryController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateCategoryMessage> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CreateCategoryCommand createCommand = CreateCategoryCommand.fromRequest(categoryRequest);
        CreateCategoryMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCategoryMessage> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        UpdateCategoryCommand updateCategoryCommand = UpdateCategoryCommand.fromRequest(id,categoryRequest);
        UpdateCategoryMessage response = mediator.send(updateCategoryCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllCategories() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {
        FindCategoryByIdQuery findCategoryByIdQuery = new FindCategoryByIdQuery(id);
        CategoryResponse categoryResponse = mediator.send(findCategoryByIdQuery);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchCategories(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetAllCategoriesQuery query = new GetAllCategoriesQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DeleteCategoryMessage> deleteCategory(@PathVariable UUID id) {
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(id);
        DeleteCategoryMessage deleteCategoryMessage =  mediator.send(deleteCategoryCommand);
        return ResponseEntity.ok(deleteCategoryMessage);
    }
}
