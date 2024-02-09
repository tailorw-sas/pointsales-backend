package com.kynsof.treatments.controller;

import com.kynsof.treatments.application.command.patientVaccine.create.CreatePatientVaccineCommand;
import com.kynsof.treatments.application.command.patientVaccine.create.CreatePatientVaccineMessage;
import com.kynsof.treatments.application.command.patientVaccine.create.CreatePatientVaccineRequest;
import com.kynsof.treatments.application.command.patientVaccine.update.UpdatePatientVaccineCommand;
import com.kynsof.treatments.application.command.patientVaccine.update.UpdatePatientVaccineMessage;
import com.kynsof.treatments.application.command.patientVaccine.update.UpdatePatientVaccineRequest;
import com.kynsof.treatments.application.query.patientVaccine.getById.FindByIdPatientVaccineQuery;
import com.kynsof.treatments.application.query.patientVaccine.getall.GetAllPatientVaccineQuery;
import com.kynsof.treatments.application.query.patientVaccine.getall.PatientVaccineResponse;
import com.kynsof.treatments.application.query.vaccine.getById.FindByIdVaccineQuery;
import com.kynsof.treatments.application.query.vaccine.getall.GetAllVaccineQuery;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.bus.IMediator;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private final IMediator mediator;

    public VaccineController(IMediator mediator){

        this.mediator = mediator;
    }

//    @PostMapping("")
//    public ResponseEntity<CreatePatientVaccineMessage> create(@RequestBody CreatePatientVaccineRequest request)  {
//        CreatePatientVaccineCommand createCommand = CreatePatientVaccineCommand.fromRequest(request);
//        CreatePatientVaccineMessage response = mediator.send(createCommand);
//
//        return ResponseEntity.ok(response);
//    }


    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String name,
                                                    @RequestParam(defaultValue = "") String description)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllVaccineQuery query = new GetAllVaccineQuery(pageable,name, description);
        PaginatedResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VaccineResponse> getById(@PathVariable UUID id) {

        FindByIdVaccineQuery query = new FindByIdVaccineQuery(id);
        VaccineResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity<UpdatePatientVaccineMessage> update(@PathVariable UUID id, @RequestBody UpdatePatientVaccineRequest request) {
//        UpdatePatientVaccineCommand command = UpdatePatientVaccineCommand.fromRequest(id,request );
//        UpdatePatientVaccineMessage response = mediator.send(command);
//        return ResponseEntity.ok(response);
//    }

}