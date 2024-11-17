package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.businessProcedure.create.CreateBusinessProcedureCommand;
import com.kynsof.treatments.application.command.businessProcedure.create.CreateBusinessProcedureMessage;
import com.kynsof.treatments.application.command.businessProcedure.create.CreateBusinessProcedureRequest;
import com.kynsof.treatments.application.command.businessProcedure.delete.DeleteBusinessProcedureCommand;
import com.kynsof.treatments.application.command.businessProcedure.delete.DeleteBusinessProcedureMessage;
import com.kynsof.treatments.application.command.businessProcedure.update.BusinessProcedurePriceUpdateRequest;
import com.kynsof.treatments.application.command.businessProcedure.update.UpdateBusinessProcedureCommand;
import com.kynsof.treatments.application.command.businessProcedure.update.UpdateBusinessProcedureMessage;
import com.kynsof.treatments.application.query.businessProcedure.getbyid.FindBusinessProcedureByIdQuery;
import com.kynsof.treatments.application.query.businessProcedure.search.BusinessProcedureResponse;
import com.kynsof.treatments.application.query.businessProcedure.search.GetSearchBusinessProcedureQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/business-procedure")
public class BusinessProcedureController {

    private final IMediator mediator;

    public BusinessProcedureController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateBusinessProcedureRequest request)  {
        CreateBusinessProcedureCommand createCommand = CreateBusinessProcedureCommand.fromRequest(request);
        CreateBusinessProcedureMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchBusinessProcedureQuery query = new GetSearchBusinessProcedureQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindBusinessProcedureByIdQuery query = new FindBusinessProcedureByIdQuery(id);
        BusinessProcedureResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }


    @PatchMapping()
    public ResponseEntity<?> update( @RequestBody BusinessProcedurePriceUpdateRequest request) {

        UpdateBusinessProcedureCommand command = UpdateBusinessProcedureCommand.fromRequest(request);
        UpdateBusinessProcedureMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        DeleteBusinessProcedureCommand query = new DeleteBusinessProcedureCommand(id);
        DeleteBusinessProcedureMessage response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}
