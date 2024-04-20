package com.kynsof.treatments.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentCommand;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentMessage;
import com.kynsof.treatments.application.command.treatment.create.CreateTreatmentRequest;
import com.kynsof.treatments.application.query.treatment.getbyid.FindByIdTreatmentQuery;
import com.kynsof.treatments.application.query.treatment.getbyid.TreatmentResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    private final IMediator mediator;

    public TreatmentController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateTreatmentRequest request) {
        CreateTreatmentCommand createCommand = CreateTreatmentCommand.fromRequest(request);
        CreateTreatmentMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TreatmentResponse> getById(@PathVariable UUID id) {

        FindByIdTreatmentQuery query = new FindByIdTreatmentQuery(id);
        TreatmentResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}
