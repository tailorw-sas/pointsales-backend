package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.currenrMedication.create.CreateCurrentMedicationCommand;
import com.kynsof.patients.application.command.currenrMedication.create.CreateCurrentMedicationEntityRequest;
import com.kynsof.patients.application.command.currenrMedication.create.CreateCurrentMedicationMessage;
import com.kynsof.patients.application.command.currenrMedication.update.UpdateCurrentMedicationCommand;
import com.kynsof.patients.application.command.currenrMedication.update.UpdateCurrentMedicationRequest;
import com.kynsof.patients.application.query.currentMedication.getById.FindByIdCurrentMedicationIQuery;
import com.kynsof.patients.application.query.currentMedication.getall.CurrentMedicationResponse;
import com.kynsof.patients.application.query.currentMedication.getall.GetAllCurrentMedicationQuery;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/medical-information/current-medication")
public class CurrentMedicationController {

    private final IMediator mediator;

    public CurrentMedicationController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateCurrentMedicationMessage> create(@RequestBody CreateCurrentMedicationEntityRequest request)  {
        CreateCurrentMedicationCommand createCommand = CreateCurrentMedicationCommand.fromRequest(request);
        CreateCurrentMedicationMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID medicalInformationId,
                                                           @RequestParam(defaultValue = "") String name)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllCurrentMedicationQuery query = new GetAllCurrentMedicationQuery(pageable, medicalInformationId, name);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CurrentMedicationResponse> getByIdA(@PathVariable UUID id) {

        FindByIdCurrentMedicationIQuery query = new FindByIdCurrentMedicationIQuery(id);
        CurrentMedicationResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateCurrentMedicationCommand> update(@PathVariable UUID id, @RequestBody UpdateCurrentMedicationRequest request) {

        UpdateCurrentMedicationCommand command = UpdateCurrentMedicationCommand.fromRequest(id,request );
        UpdateCurrentMedicationCommand response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}