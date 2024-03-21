package com.kynsof.patients.controller;

import com.kynsof.patients.application.query.geographicLocation.findCantonAndProvinceIdsByParroquiaId.LocationHierarchyQuery;
import com.kynsof.patients.application.query.geographicLocation.findCantonAndProvinceIdsByParroquiaId.LocationHierarchyResponse;
import com.kynsof.patients.application.query.geographicLocation.getById.FindByIdGeographicLocationQuery;
import com.kynsof.patients.application.query.geographicLocation.getall.GeographicLocationResponse;
import com.kynsof.patients.application.query.geographicLocation.search.GetSearchLocationsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/locations")
public class GeographicLocationController {

    private final IMediator mediator;

    public GeographicLocationController(IMediator mediator){

        this.mediator = mediator;
    }
    @PostMapping("/search")
    @PreAuthorize("permitAll()")
    @CrossOrigin(origins = "http://localhost:9000")
    public Mono<ResponseEntity<PaginatedResponse>> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchLocationsQuery query = new GetSearchLocationsQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return Mono.just(ResponseEntity.ok(data));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GeographicLocationResponse> getById(@PathVariable UUID id) {

        FindByIdGeographicLocationQuery query = new FindByIdGeographicLocationQuery(id);
        GeographicLocationResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/parroquia/{id}")
    public ResponseEntity<LocationHierarchyResponse> FindByIdGeographicLocation(@PathVariable UUID id) {

        LocationHierarchyQuery query = new LocationHierarchyQuery(id);
        LocationHierarchyResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }
}