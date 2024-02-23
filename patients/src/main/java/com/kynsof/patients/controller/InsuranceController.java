package com.kynsof.patients.controller;

import com.kynsof.patients.application.query.insuarance.getById.FindByIdInsuranceQuery;
import com.kynsof.patients.application.query.insuarance.getall.GetAllInsuranceQuery;
import com.kynsof.patients.application.query.insuarance.getall.InsuranceResponse;
import com.kynsof.patients.domain.dto.PaginatedResponse;
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