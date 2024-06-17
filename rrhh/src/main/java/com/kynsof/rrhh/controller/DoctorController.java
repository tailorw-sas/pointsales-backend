package com.kynsof.rrhh.controller;


import com.kynsof.rrhh.application.command.doctor.create.CreateDoctorCommand;
import com.kynsof.rrhh.application.command.doctor.create.CreateDoctorMessage;
import com.kynsof.rrhh.application.command.doctor.create.CreateDoctorRequest;
import com.kynsof.rrhh.application.command.doctor.delete.DeleteDoctorCommand;
import com.kynsof.rrhh.application.command.doctor.delete.DeleteDoctorMessage;
import com.kynsof.rrhh.application.command.doctor.update.UpdateDoctorCommand;
import com.kynsof.rrhh.application.command.doctor.update.UpdateDoctorMessage;
import com.kynsof.rrhh.application.command.doctor.update.UpdateDoctorRequest;
import com.kynsof.rrhh.application.query.doctor.getbyid.DoctorResponse;
import com.kynsof.rrhh.application.query.doctor.getbyid.FindByIdDoctorQuery;
import com.kynsof.rrhh.application.query.doctor.search.GetSearchDoctorQuery;
import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final IMediator mediator;

    public DoctorController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateDoctorRequest request)  {
        CreateDoctorCommand createCommand = CreateDoctorCommand.fromRequest(request);
        CreateDoctorMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindByIdDoctorQuery query = new FindByIdDoctorQuery(id);
        DoctorResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchDoctorQuery query = new GetSearchDoctorQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {

        DeleteDoctorCommand command = new DeleteDoctorCommand(id);
        DeleteDoctorMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id,@RequestBody UpdateDoctorRequest request) {

        UpdateDoctorCommand command = UpdateDoctorCommand.fromRequest(request,id);
        UpdateDoctorMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
