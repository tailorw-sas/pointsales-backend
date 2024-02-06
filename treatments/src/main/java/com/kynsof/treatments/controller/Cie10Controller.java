package com.kynsof.treatments.controller;

import com.kynsof.treatments.application.command.externalConsultation.create.CreateExternalConsultationCommand;
import com.kynsof.treatments.application.command.externalConsultation.create.CreateExternalConsultationMessage;
import com.kynsof.treatments.application.command.externalConsultation.create.CreateExternalConsultationRequest;
import com.kynsof.treatments.application.command.externalConsultation.update.UpdateExternalConsultationCommand;
import com.kynsof.treatments.application.command.externalConsultation.update.UpdateExternalConsultationMessage;
import com.kynsof.treatments.application.command.externalConsultation.update.UpdateExternalConsultationRequest;
import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.application.query.cie10.getAll.GetAllCie10Query;
import com.kynsof.treatments.application.query.cie10.getByCode.FindByCodeCie10Query;
import com.kynsof.treatments.application.query.externalConsultation.getById.FindByIdExternalConsultationQuery;
import com.kynsof.treatments.application.query.externalConsultation.getall.ExternalConsultationResponse;
import com.kynsof.treatments.application.query.externalConsultation.getall.GetAllExternalConsultationQuery;
import com.kynsof.treatments.domain.bus.IMediator;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cie10")
public class Cie10Controller {

    private final IMediator mediator;

    public Cie10Controller(IMediator mediator){

        this.mediator = mediator;
    }



    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String name,
                                                           @RequestParam(defaultValue = "") String code)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllCie10Query query = new GetAllCie10Query(pageable, name, code);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<Cie10Response> getById(@PathVariable String code) {

        FindByCodeCie10Query query = new FindByCodeCie10Query(code);
        Cie10Response response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

}