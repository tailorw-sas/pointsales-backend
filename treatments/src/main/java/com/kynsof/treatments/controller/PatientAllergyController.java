package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.diagnosis.delete.DiagnosisDeleteCommand;
import com.kynsof.treatments.application.command.diagnosis.delete.DiagnosisDeleteMessage;
import com.kynsof.treatments.application.command.patientAllergy.create.CreatePatientAllergyCommand;
import com.kynsof.treatments.application.command.patientAllergy.create.CreatePatientAllergyMessage;
import com.kynsof.treatments.application.command.patientAllergy.create.CreatePatientAllergyRequest;
import com.kynsof.treatments.application.command.patientAllergy.update.UpdatePatientAllergyCommand;
import com.kynsof.treatments.application.command.patientAllergy.update.UpdatePatientAllergyMessage;
import com.kynsof.treatments.application.command.patientAllergy.update.UpdatePatientAllergyRequest;
import com.kynsof.treatments.application.query.paymentAllergy.getbyid.FindByIdPatientAllergyQuery;
import com.kynsof.treatments.application.query.paymentAllergy.getbyid.PaymentAllergyResponse;
import com.kynsof.treatments.application.query.paymentAllergy.search.GetSearchPaymentAllergyQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient-allergy")
public class PatientAllergyController {

    private final IMediator mediator;

    public PatientAllergyController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreatePatientAllergyRequest request) {
        CreatePatientAllergyCommand createCommand =  CreatePatientAllergyCommand.fromRequest(request);
        CreatePatientAllergyMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        FindByIdPatientAllergyQuery query = new FindByIdPatientAllergyQuery(id);
        PaymentAllergyResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody UpdatePatientAllergyRequest request) {

        UpdatePatientAllergyCommand command = UpdatePatientAllergyCommand.fromRequest(id,request);
        UpdatePatientAllergyMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchPaymentAllergyQuery query = new GetSearchPaymentAllergyQuery(pageable, request.getFilter(), request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        DiagnosisDeleteCommand query = new DiagnosisDeleteCommand(id);
        DiagnosisDeleteMessage response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

}
