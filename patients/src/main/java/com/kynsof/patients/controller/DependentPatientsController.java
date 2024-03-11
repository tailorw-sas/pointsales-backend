package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientMessage;
import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientsCommand;
import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientsRequest;
import com.kynsof.patients.application.command.dependents.createByPatientId.CreateDependentByPatientIdCommand;
import com.kynsof.patients.application.command.dependents.createByPatientId.CreateDependentByPatientIdMessage;
import com.kynsof.patients.application.command.dependents.createByPatientId.CreateDependentByPatientIdRequest;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientMessage;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientsCommand;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientsRequest;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dependent-patients")
public class DependentPatientsController {

    private final IMediator mediator;

    public DependentPatientsController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateDependentPatientMessage> create(@RequestBody CreateDependentPatientsRequest request)  {
        CreateDependentPatientsCommand createCommand = CreateDependentPatientsCommand.fromRequest(request);
        CreateDependentPatientMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/patientId")
    public ResponseEntity<CreateDependentByPatientIdMessage> createByPatientId(@RequestBody CreateDependentByPatientIdRequest request)  {
        CreateDependentByPatientIdCommand createCommand = CreateDependentByPatientIdCommand.fromRequest(request);
        CreateDependentByPatientIdMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateDependentPatientMessage> update(@PathVariable UUID id, @RequestBody UpdateDependentPatientsRequest request) {

        UpdateDependentPatientsCommand command = UpdateDependentPatientsCommand.fromRequest(id,request );
        UpdateDependentPatientMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }


}
