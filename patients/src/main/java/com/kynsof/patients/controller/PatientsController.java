package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.patients.create.CreatePatientMessage;
import com.kynsof.patients.application.command.patients.create.CreatePatientsCommand;
import com.kynsof.patients.application.command.patients.create.request.CreatePatientsRequest;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceCommand;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceMessage;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceRequest;
import com.kynsof.patients.application.command.patients.delete.DeletePatientsCommand;
import com.kynsof.patients.application.command.patients.delete.PatientDeleteMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsRequest;
import com.kynsof.patients.application.query.patients.getById.FindPatientsByIdQuery;
import com.kynsof.patients.application.query.patients.getByIdentification.FindPatientsByIdentificationQuery;
import com.kynsof.patients.application.query.patients.getall.GetAllPatientsFilterQuery;
import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.application.query.patients.search.GetSearchPatientsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {
    private final IMediator mediator;
    public PatientsController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreatePatientMessage> create(@RequestBody CreatePatientsRequest request)  {
        CreatePatientsCommand createCommand = CreatePatientsCommand.fromRequest(request);
        CreatePatientMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/insurance")
    public ResponseEntity<CreateInsuranceMessage> createInsurance(@RequestBody CreateInsuranceRequest request)  {
        CreateInsuranceCommand createCommand = CreateInsuranceCommand.fromRequest(request);
        CreateInsuranceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID patientId,
                                                    @RequestParam(defaultValue = "") UUID primeId,
                                                    @RequestParam(defaultValue = "") String identification)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllPatientsFilterQuery query = new GetAllPatientsFilterQuery(pageable, patientId, identification,primeId);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchPatientsQuery query = new GetSearchPatientsQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientsResponse> getById(@PathVariable UUID id) {

        FindPatientsByIdQuery query = new FindPatientsByIdQuery(id);
        PatientsResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/identification/{identification}")
    public ResponseEntity<PatientsResponse> getByIdentification(@PathVariable String identification) {

        FindPatientsByIdentificationQuery query = new FindPatientsByIdentificationQuery(identification);
        PatientsResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UpdatePatientMessage> update(@PathVariable UUID id, @RequestBody UpdatePatientsRequest request) {

        UpdatePatientsCommand command = UpdatePatientsCommand.fromRequest(id,request );
        UpdatePatientMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDeleteMessage> deleteServices(@PathVariable("id") UUID id) {

        DeletePatientsCommand command = new DeletePatientsCommand(id);
        PatientDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
