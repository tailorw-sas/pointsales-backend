package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.additionalInfo.delete.DeleteAdditionalInfoCommand;
import com.kynsof.patients.application.command.additionalInfo.delete.DeleteAdditionalMessage;
import com.kynsof.patients.application.command.allergy.create.CreateAllergyCommand;
import com.kynsof.patients.application.command.allergy.create.CreateAllergyMessage;
import com.kynsof.patients.application.command.allergy.create.CreateAllergyEntityRequest;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyCommand;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyMessage;
import com.kynsof.patients.application.command.allergy.update.UpdateAllergyRequest;
import com.kynsof.patients.application.command.medicalInformation.create.CreateMedicalInformationCommand;
import com.kynsof.patients.application.command.medicalInformation.create.CreateMedicalInformationMessage;
import com.kynsof.patients.application.command.medicalInformation.create.CreateMedicalInformationRequest;
import com.kynsof.patients.application.command.medicalInformation.update.UpdateMedicalInformationCommand;
import com.kynsof.patients.application.command.medicalInformation.update.UpdateMedicalInformationMessage;
import com.kynsof.patients.application.command.medicalInformation.update.UpdateMedicalInformationRequest;
import com.kynsof.patients.application.query.medicalInformation.getById.FindByIdMedicalInformationQuery;
import com.kynsof.patients.application.query.medicalInformation.getall.GetMedicalInformationQuery;
import com.kynsof.patients.application.query.medicalInformation.getall.MedicalInformationResponse;
import com.kynsof.patients.domain.bus.IMediator;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/medical-information")
public class MedicalInformationController {

    private final IMediator mediator;

    public MedicalInformationController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateMedicalInformationMessage> create(@RequestBody CreateMedicalInformationRequest request)  {
        CreateMedicalInformationCommand createCommand = CreateMedicalInformationCommand.fromRequest(request);
        CreateMedicalInformationMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/allergy")
    public ResponseEntity<CreateAllergyMessage> createAllergy(@RequestBody CreateAllergyEntityRequest request)  {
        CreateAllergyCommand createCommand = CreateAllergyCommand.fromRequest(request);
        CreateAllergyMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idPatients,
                                                    @RequestParam(defaultValue = "") String emergencyContactName)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetMedicalInformationQuery query = new GetMedicalInformationQuery(pageable, idPatients, emergencyContactName);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicalInformationResponse> getById(@PathVariable UUID id) {

        FindByIdMedicalInformationQuery query = new FindByIdMedicalInformationQuery(id);
        MedicalInformationResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateMedicalInformationMessage> update(@PathVariable UUID id, @RequestBody UpdateMedicalInformationRequest request) {

        UpdateMedicalInformationCommand command = UpdateMedicalInformationCommand.fromRequest(id,request );
        UpdateMedicalInformationMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/allergy/{id}")
    public ResponseEntity<UpdateAllergyMessage> updateAllergy(@PathVariable UUID id, @RequestBody UpdateAllergyRequest request) {

        UpdateAllergyCommand command = UpdateAllergyCommand.fromRequest(id,request );
        UpdateAllergyMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteAdditionalMessage> deleteServices(@PathVariable("id") UUID id) {

        DeleteAdditionalInfoCommand command = new DeleteAdditionalInfoCommand(id);
        DeleteAdditionalMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}