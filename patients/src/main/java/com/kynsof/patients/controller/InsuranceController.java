package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.insurance.create.CreateNewInsuranceCommand;
import com.kynsof.patients.application.command.insurance.create.CreateNewInsuranceMessage;
import com.kynsof.patients.application.command.insurance.create.CreateNewInsuranceRequest;
import com.kynsof.patients.application.command.insurance.update.UpdateInsuranceCommand;
import com.kynsof.patients.application.command.insurance.update.UpdateInsuranceMessage;
import com.kynsof.patients.application.command.insurance.update.UpdateInsuranceRequest;
import com.kynsof.patients.application.query.insuarance.getById.FindByIdInsuranceQuery;
import com.kynsof.patients.application.query.insuarance.getall.GetAllInsuranceQuery;
import com.kynsof.patients.application.query.insuarance.getall.InsuranceResponse;
import com.kynsof.patients.application.query.insuarance.search.GetSearchInsuranceQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final IMediator mediator;

    public InsuranceController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateNewInsuranceRequest request) {
        CreateNewInsuranceCommand createCommand = CreateNewInsuranceCommand.fromRequest(request);
        CreateNewInsuranceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody UpdateInsuranceRequest request) {

        UpdateInsuranceCommand command = UpdateInsuranceCommand.fromRequest(request, id);
        UpdateInsuranceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchInsuranceQuery query = new GetSearchInsuranceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String name)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllInsuranceQuery query = new GetAllInsuranceQuery(pageable, name);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InsuranceResponse> getById(@PathVariable UUID id) {

        FindByIdInsuranceQuery query = new FindByIdInsuranceQuery(id);
        InsuranceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}