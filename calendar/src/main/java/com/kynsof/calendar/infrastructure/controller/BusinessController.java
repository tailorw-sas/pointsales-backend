package com.kynsof.calendar.infrastructure.controller;

import com.kynsof.calendar.application.query.BusinessResponse;
import com.kynsof.calendar.application.query.business.findBusinessesWithAvailableStock.FindBusinessesWithAvailableStockQuery;
import com.kynsof.calendar.application.query.business.findBusinessesWithAvailableStock.findBusinessesWithAvailableStockByDateAndServiceRequest;
import com.kynsof.calendar.application.query.business.getbyid.FindBusinessByIdQuery;
import com.kynsof.calendar.application.query.business.search.GetSearchBusinessQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final IMediator mediator;

    public BusinessController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchBusinessQuery query = new GetSearchBusinessQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BusinessResponse> getById(@PathVariable UUID id) {

        FindBusinessByIdQuery query = new FindBusinessByIdQuery(id);
        BusinessResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/with-available-stock")
    public ResponseEntity<?> findBusinessesWithAvailableStock(
            @RequestBody findBusinessesWithAvailableStockByDateAndServiceRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        FindBusinessesWithAvailableStockQuery query = new FindBusinessesWithAvailableStockQuery(pageable, request.getDate(),
                request.getServiceId());
        PaginatedResponse response= mediator.send(query);
        return ResponseEntity.ok(response);
    }

}
