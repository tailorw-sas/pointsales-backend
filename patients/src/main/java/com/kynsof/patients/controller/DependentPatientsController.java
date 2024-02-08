package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientMessage;
import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientsCommand;
import com.kynsof.patients.application.command.dependents.create.CreateDependentPatientsRequest;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientMessage;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientsCommand;
import com.kynsof.patients.application.command.dependents.update.UpdateDependentPatientsRequest;
import com.kynsof.patients.application.command.patients.create.CreatePatientMessage;
import com.kynsof.patients.application.command.patients.create.CreatePatientsCommand;
import com.kynsof.patients.application.command.patients.create.CreatePatientsRequest;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceCommand;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceMessage;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceRequest;
import com.kynsof.patients.application.command.patients.delete.PatientDeleteMessage;
import com.kynsof.patients.application.command.patients.delete.PatientsDeleteCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsRequest;
import com.kynsof.patients.application.query.patients.getById.FindPatientsByIdQuery;
import com.kynsof.patients.application.query.patients.getall.GetAllPatientsFilterQuery;
import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.domain.bus.IMediator;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateDependentPatientMessage> update(@PathVariable UUID id, @RequestBody UpdateDependentPatientsRequest request) {

        UpdateDependentPatientsCommand command = UpdateDependentPatientsCommand.fromRequest(id,request );
        UpdateDependentPatientMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }


}
