package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentCommand;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentMessage;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentRequest;
import com.kynsof.treatments.application.command.treatment.createall.CreateAllTreatmentCommand;
import com.kynsof.treatments.application.command.treatment.createall.CreateAllTreatmentMessage;
import com.kynsof.treatments.application.command.treatment.createall.PayloadTreatment;
import com.kynsof.treatments.application.command.treatment.update.UpdateTreatmentCommand;
import com.kynsof.treatments.application.command.treatment.update.UpdateTreatmentMessage;
import com.kynsof.treatments.application.command.treatment.update.UpdateTreatmentRequest;
import com.kynsof.treatments.application.query.treatment.getbyid.FindByIdTreatmentQuery;
import com.kynsof.treatments.application.query.treatment.getbyid.TreatmentResponse;
import com.kynsof.treatments.application.query.treatment.getdiagnosisbyidexternalconsultation.FindTreatmentByIdExternalConsultationQuery;
import com.kynsof.treatments.application.query.treatment.search.GetSearchTreatmentQuery;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external-consultation/treatment")
public class TreatmentController {

    private final IMediator mediator;

    public TreatmentController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("/one")
    public ResponseEntity<?> create(@RequestBody CreateTreatmentRequest request) {
        CreateTreatmentCommand createCommand = CreateTreatmentCommand.fromRequest(request);
        CreateTreatmentMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> createAll(@RequestBody PayloadTreatment request) {
        CreateAllTreatmentCommand createCommand = new CreateAllTreatmentCommand(request);
        CreateAllTreatmentMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TreatmentResponse> getById(@PathVariable UUID id) {

        FindByIdTreatmentQuery query = new FindByIdTreatmentQuery(id);
        TreatmentResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/by-id-external-consultation/{id}")
    public ResponseEntity<PaginatedResponse> findDiagnosisByIdExternalConsultation(@PathVariable UUID id) {

        Pageable pageable = PageRequest.of(0, 1000);
        FindTreatmentByIdExternalConsultationQuery query = new FindTreatmentByIdExternalConsultationQuery(id, pageable);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchTreatmentQuery query = new GetSearchTreatmentQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTreatmentMessage> update(@PathVariable("id") UUID id, @RequestBody UpdateTreatmentRequest request) {

        UpdateTreatmentCommand command = UpdateTreatmentCommand.fromRequest(request, id);
        UpdateTreatmentMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}
