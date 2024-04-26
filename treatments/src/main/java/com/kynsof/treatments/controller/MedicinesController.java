package com.kynsof.treatments.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineCommand;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineMessage;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import com.kynsof.treatments.application.command.medicine.createall.CreateAllMedicineMessage;
import com.kynsof.treatments.application.command.medicine.createall.CreateAllMedicinesCommand;
import com.kynsof.treatments.application.command.medicine.createall.PayloadMedicine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external-consultation/treatment/medicine")
public class MedicinesController {

    private final IMediator mediator;

    public MedicinesController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("/one")
    public ResponseEntity<?> create(@RequestBody CreateMedicineRequest request) {
        CreateMedicineCommand createCommand = CreateMedicineCommand.fromRequest(request);
        CreateMedicineMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> createAll(@RequestBody PayloadMedicine request) {
        CreateAllMedicinesCommand createCommand = new CreateAllMedicinesCommand(request);
        CreateAllMedicineMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

}
