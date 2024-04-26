package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineCommand;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineMessage;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import com.kynsof.treatments.application.command.medicine.createall.CreateAllMedicineMessage;
import com.kynsof.treatments.application.command.medicine.createall.CreateAllMedicinesCommand;
import com.kynsof.treatments.application.query.medicine.getbyid.FindByIdMedicinesQuery;
import com.kynsof.treatments.application.query.medicine.getbyid.MedicinesResponse;
import com.kynsof.treatments.application.query.medicine.search.GetSearchMedicinesQuery;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> createAll(@RequestBody List<String> payload) {
        CreateAllMedicinesCommand createCommand = new CreateAllMedicinesCommand(payload);
        CreateAllMedicineMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicinesResponse> getById(@PathVariable UUID id) {

        FindByIdMedicinesQuery query = new FindByIdMedicinesQuery(id);
        MedicinesResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());
        GetSearchMedicinesQuery query = new GetSearchMedicinesQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

}
