package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.patients.create.CreatePatientMessage;
import com.kynsof.patients.application.command.patients.create.CreatePatientsCommand;
import com.kynsof.patients.application.command.patients.create.request.CreatePatientsRequest;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceCommand;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceMessage;
import com.kynsof.patients.application.command.patients.createInsurance.CreateInsuranceRequest;
import com.kynsof.patients.application.command.patients.createadmin.CreatePatientAdminCommand;
import com.kynsof.patients.application.command.patients.createadmin.CreatePatientAdminMessage;
import com.kynsof.patients.application.command.patients.createadmin.CreatePatientsAdminRequest;
import com.kynsof.patients.application.command.patients.delete.DeletePatientsCommand;
import com.kynsof.patients.application.command.patients.delete.PatientDeleteMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsRequest;
import com.kynsof.patients.application.command.patients.updateadmin.UpdatePatientAdminCommand;
import com.kynsof.patients.application.command.patients.updateadmin.UpdatePatientAdminMessage;
import com.kynsof.patients.application.command.patients.updateadmin.UpdatePatientsAdminRequest;
import com.kynsof.patients.application.query.patients.getById.FindPatientsByIdQuery;
import com.kynsof.patients.application.query.patients.getById.PatientByIdResponse;
import com.kynsof.patients.application.query.patients.getByIdentification.FindPatientsByIdentificationQuery;
import com.kynsof.patients.application.query.patients.getall.GetAllPatientsFilterQuery;
import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.application.query.patients.search.GetSearchPatientsQuery;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.ApiError;
import com.kynsof.share.core.domain.response.ApiResponse;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final IMediator mediator;

    public PatientsController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreatePatientMessage> create(@RequestBody CreatePatientsRequest request) {
        CreatePatientsCommand createCommand = CreatePatientsCommand.fromRequest(request);
        CreatePatientMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/admin/create_patients/{patientId}")
    public ResponseEntity<?> create(@PathVariable UUID patientId, @RequestBody CreatePatientsAdminRequest request) {
        CreatePatientAdminCommand createCommand = CreatePatientAdminCommand.fromRequest(patientId,request);
        CreatePatientAdminMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/insurance")
    public ResponseEntity<CreateInsuranceMessage> createInsurance(@RequestBody CreateInsuranceRequest request) {
        CreateInsuranceCommand createCommand = CreateInsuranceCommand.fromRequest(request);
        CreateInsuranceMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") UUID patientId,
            @RequestParam(defaultValue = "") UUID primeId,
            @RequestParam(defaultValue = "") String identification) {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllPatientsFilterQuery query = new GetAllPatientsFilterQuery(pageable, patientId, identification, primeId);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchPatientsQuery query = new GetSearchPatientsQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientByIdResponse> getById(@PathVariable UUID id) {

        FindPatientsByIdQuery query = new FindPatientsByIdQuery(id);
        PatientByIdResponse response = mediator.send(query);

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

        UpdatePatientsCommand command = UpdatePatientsCommand.fromRequest(id, request);
        UpdatePatientMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/admin/update_patients/{id}")
    public ResponseEntity<UpdatePatientAdminMessage> update(@PathVariable UUID id, @RequestBody UpdatePatientsAdminRequest request) {

        UpdatePatientAdminCommand command = UpdatePatientAdminCommand.fromRequest(request, id);
        UpdatePatientAdminMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDeleteMessage> deleteServices(@PathVariable("id") UUID id) {

        DeletePatientsCommand command = new DeletePatientsCommand(id);
        PatientDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> me(@AuthenticationPrincipal Jwt jwt) {
        try {
            String patientId = jwt.getClaim("sub");
            FindPatientsByIdQuery query = new FindPatientsByIdQuery(UUID.fromString(patientId));
            PatientByIdResponse response = mediator.send(query);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.fail(ApiError.withSingleError("error", "token", "Error al procesar el token")));
        }

    }
}
