package com.kynsof.store.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.store.application.command.customer.create.CreateCustomerCommand;
import com.kynsof.store.application.command.customer.create.CreateCustomerMessage;
import com.kynsof.store.application.command.customer.create.CustomerRequest;
import com.kynsof.store.application.command.customer.delete.DeleteCustomerCommand;
import com.kynsof.store.application.command.customer.delete.DeleteCustomerMessage;
import com.kynsof.store.application.command.customer.update.UpdateCustomerCommand;
import com.kynsof.store.application.command.customer.update.UpdateCustomerMessage;
import com.kynsof.store.application.query.customer.getAll.CustomerResponse;
import com.kynsof.store.application.query.customer.getAll.GetAllCustomerQuery;
import com.kynsof.store.application.query.customer.getById.FindCustomerByIdQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final IMediator mediator;

    public CustomerController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateCustomerMessage> createCategory(@RequestBody CustomerRequest categoryRequest) {
        CreateCustomerCommand createCommand = CreateCustomerCommand.fromRequest(categoryRequest);
        CreateCustomerMessage response = mediator.send(createCommand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCustomerMessage> updateCategory(@PathVariable UUID id, @RequestBody CustomerRequest categoryRequest) {
        UpdateCustomerCommand updateCategoryCommand = UpdateCustomerCommand.fromRequest(id,categoryRequest);
        UpdateCustomerMessage response = mediator.send(updateCategoryCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllCategories() {
        return Mono.just(ResponseEntity.ok("Spring Boot: Keycloak with ADMIN CLIENT ROLE"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCategoryById(@PathVariable UUID id) {
        FindCustomerByIdQuery findCategoryByIdQuery = new FindCustomerByIdQuery(id);
        CustomerResponse customerResponse = mediator.send(findCategoryByIdQuery);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> searchCategories(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetAllCustomerQuery query = new GetAllCustomerQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DeleteCustomerMessage> deleteCategory(@PathVariable UUID id) {
        DeleteCustomerCommand deleteCategoryCommand = new DeleteCustomerCommand(id);
        DeleteCustomerMessage customerMessage =  mediator.send(deleteCategoryCommand);
        return ResponseEntity.ok(customerMessage);
    }
}
