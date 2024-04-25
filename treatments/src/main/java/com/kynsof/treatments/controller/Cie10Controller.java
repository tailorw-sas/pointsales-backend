package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.query.cei10.search.GetSearchCie10Query;
import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.application.query.cie10.getAll.GetAllCie10Query;
import com.kynsof.treatments.application.query.cie10.getByCode.FindByCodeCie10Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cie10")
public class Cie10Controller {

    private final IMediator mediator;

    public Cie10Controller(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchCie10Query query = new GetSearchCie10Query(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String code) {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllCie10Query query = new GetAllCie10Query(pageable, name, code);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<Cie10Response> getById(@PathVariable String code) {

        FindByCodeCie10Query query = new FindByCodeCie10Query(code);
        Cie10Response response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
