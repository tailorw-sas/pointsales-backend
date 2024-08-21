package com.kynsof.shift.controller;

import com.kynsof.shift.application.command.resource.addServices.AddServiceCommand;
import com.kynsof.shift.application.command.resource.addServices.AddServiceMessage;
import com.kynsof.shift.application.command.resource.addServices.CreateResourceRequest;
import com.kynsof.shift.application.command.resource.delete.ResourceDeleteCommand;
import com.kynsof.shift.application.command.resource.delete.ResourceDeleteMessage;
import com.kynsof.shift.application.command.resource.update.UpdateResourceCommand;
import com.kynsof.shift.application.command.resource.update.UpdateResourceMessage;
import com.kynsof.shift.application.command.resource.update.UpdateResourceRequest;
import com.kynsof.shift.application.query.ResourceResponse;
import com.kynsof.shift.application.query.resource.findResourcesWithAvailableSchedules.ScheduleSearchCriteriaRequest;
import com.kynsof.shift.application.query.resource.findResourcesWithAvailableSchedules.findResourcesWithAvailableSchedulesQuery;
import com.kynsof.shift.application.query.resource.getServiceByBusinessIdByResourceId.getServiceByBusinessIdByResourceIdQuery;
import com.kynsof.shift.application.query.resource.getbyid.FindResourceByIdQuery;
import com.kynsof.shift.application.query.resource.getresourcesbyidservice.FindResourcesByIdServiceQuery;
import com.kynsof.shift.application.query.resource.search.GetSearchResourceQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final IMediator mediator;

    public ResourceController(IMediator mediator){

        this.mediator = mediator;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResourceResponse> getById(@PathVariable UUID id) {

        FindResourceByIdQuery query = new FindResourceByIdQuery(id);
        ResourceResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/business/{businessId}/services/{serviceId}")
    public ResponseEntity<?> findServicesByResourceId(@PathVariable UUID businessId, @PathVariable UUID serviceId) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindResourcesByIdServiceQuery query = new FindResourcesByIdServiceQuery(businessId,serviceId, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchResourceQuery query = new GetSearchResourceQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceDeleteMessage> delete(@PathVariable("id") UUID id) {

        ResourceDeleteCommand command = new ResourceDeleteCommand(id);
        ResourceDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateResourceMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateResourceRequest request) {
        UpdateResourceCommand command = UpdateResourceCommand.fromRequest(id,request);
        UpdateResourceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/available")
    public ResponseEntity<PaginatedResponse> findResourcesWithAvailableSchedules(@RequestBody ScheduleSearchCriteriaRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        findResourcesWithAvailableSchedulesQuery query = new findResourcesWithAvailableSchedulesQuery(pageable,
                request.getDate(),
                request.getServiceId(),
                request.getBusinessId());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PostMapping()
    public ResponseEntity<?> create( @RequestBody CreateResourceRequest request)
    {
        AddServiceCommand command = AddServiceCommand.fromRequest(request);
        AddServiceMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resourceId}/services/{businessId}")
    public ResponseEntity<?> getAllServicesByResourceAndBusiness(
            @PathVariable UUID resourceId,
            @PathVariable UUID businessId) {
        getServiceByBusinessIdByResourceIdQuery query = new getServiceByBusinessIdByResourceIdQuery(resourceId, businessId);
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
