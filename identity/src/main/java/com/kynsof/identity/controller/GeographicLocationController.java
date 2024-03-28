package com.kynsof.identity.controller;

import com.kynsof.identity.application.command.geographiclocations.create.CreateGeographicLocationCommand;
import com.kynsof.identity.application.command.geographiclocations.create.CreateGeographicLocationMessage;
import com.kynsof.identity.application.command.geographiclocations.create.CreateGeographicLocationRequest;
import com.kynsof.identity.application.query.business.geographiclocation.findcantonandprovinceIdsbyparroquiaid.LocationHierarchyQuery;
import com.kynsof.identity.application.query.business.geographiclocation.findcantonandprovinceIdsbyparroquiaid.LocationHierarchyResponse;
import com.kynsof.identity.application.query.business.geographiclocation.getall.GeographicLocationResponse;
import com.kynsof.identity.application.query.business.geographiclocation.getbyid.FindByIdGeographicLocationQuery;
import com.kynsof.identity.application.query.business.geographiclocation.search.GetSearchLocationsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
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

    @PostMapping("")
    public ResponseEntity<CreateGeographicLocationMessage> create(@RequestBody CreateGeographicLocationRequest request)  {
        CreateGeographicLocationCommand createCommand = CreateGeographicLocationCommand.fromRequest(request);
        CreateGeographicLocationMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
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

    @GetMapping(path = "/parroquia/{id}")
    public ResponseEntity<LocationHierarchyResponse> FindByIdGeographicLocation(@PathVariable UUID id) {

        LocationHierarchyQuery query = new LocationHierarchyQuery(id);
        LocationHierarchyResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }
}