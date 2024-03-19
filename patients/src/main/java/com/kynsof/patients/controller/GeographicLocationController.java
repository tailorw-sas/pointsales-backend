package com.kynsof.patients.controller;

import com.kynsof.patients.application.query.geographicLocation.getById.FindByIdGeographicLocationQuery;
import com.kynsof.patients.application.query.geographicLocation.getall.GeographicLocationResponse;
import com.kynsof.patients.application.query.geographicLocation.search.GetSearchLocationsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class GeographicLocationController {

    private final IMediator mediator;

    public GeographicLocationController(IMediator mediator){

        this.mediator = mediator;
    }
    @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchLocationsQuery query = new GetSearchLocationsQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GeographicLocationResponse> getById(@PathVariable UUID id) {

        FindByIdGeographicLocationQuery query = new FindByIdGeographicLocationQuery(id);
        GeographicLocationResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }
}