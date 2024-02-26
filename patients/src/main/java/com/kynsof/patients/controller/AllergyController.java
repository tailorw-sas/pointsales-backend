package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.allergy.create.CreateAllergyCommand;
import com.kynsof.patients.application.command.allergy.create.CreateAllergyEntityRequest;
import com.kynsof.patients.application.command.allergy.create.CreateAllergyMessage;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyCommand;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyEntityRequest;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyMessage;
import com.kynsof.patients.application.query.allergy.getById.FindByIdAllergyQuery;
import com.kynsof.patients.application.query.allergy.getall.AllergyResponse;
import com.kynsof.patients.application.query.allergy.getall.GetAllAllergyQuery;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/medical-information/allergy")
public class AllergyController {

    private final IMediator mediator;

    public AllergyController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateAllergyMessage> createAllergy(@RequestBody CreateAllergyEntityRequest request)  {
        CreateAllergyCommand createCommand = CreateAllergyCommand.fromRequest(request);
        CreateAllergyMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAllAllergy(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID medicalInformationId,
                                                           @RequestParam(defaultValue = "") String name,
                                                    @RequestParam(defaultValue = "") String code)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllAllergyQuery query = new GetAllAllergyQuery(pageable, medicalInformationId, name, code);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AllergyResponse> getByIdAllergy(@PathVariable UUID id) {

        FindByIdAllergyQuery query = new FindByIdAllergyQuery(id);
        AllergyResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateAllergyMessage> updateAllergy(@PathVariable UUID id, @RequestBody UpdateAllergyEntityRequest request) {

        UpdateAllergyCommand command = UpdateAllergyCommand.fromRequest(id,request );
        UpdateAllergyMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

}