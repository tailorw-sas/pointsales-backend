package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.additionalInfo.create.CreateAdditionalInfoCommand;
import com.kynsof.patients.application.command.additionalInfo.create.CreateAdditionalInfoRequest;
import com.kynsof.patients.application.command.additionalInfo.delete.DeleteAdditionalInfoCommand;
import com.kynsof.patients.application.command.additionalInfo.delete.DeleteAdditionalMessage;
import com.kynsof.patients.application.command.additionalInfo.update.UpdateAdditionalInfoCommand;
import com.kynsof.patients.application.command.additionalInfo.update.UpdateAdditionalInfoMessage;
import com.kynsof.patients.application.command.additionalInfo.update.UpdateAdditionalInfoRequest;
import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoMessage;
import com.kynsof.patients.application.query.additionalInfo.getById.FindByIdAdditionalInfoQuery;
import com.kynsof.patients.application.query.additionalInfo.getall.AdditionalInfoResponse;
import com.kynsof.patients.application.query.additionalInfo.getall.GetAllAdditionalInfoQuery;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/additional-information")
public class AdditionalInformationController {

    private final IMediator mediator;

    public AdditionalInformationController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateContactInfoMessage> create(@RequestBody CreateAdditionalInfoRequest request)  {
        CreateAdditionalInfoCommand createCommand = CreateAdditionalInfoCommand.fromRequest(request);
        CreateContactInfoMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idPatients,
                                                    @RequestParam(defaultValue = "") String emergencyContactName)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllAdditionalInfoQuery query = new GetAllAdditionalInfoQuery(pageable, idPatients, emergencyContactName);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AdditionalInfoResponse> getById(@PathVariable UUID id) {

        FindByIdAdditionalInfoQuery query = new FindByIdAdditionalInfoQuery(id);
        AdditionalInfoResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateAdditionalInfoMessage> update(@PathVariable UUID id, @RequestBody UpdateAdditionalInfoRequest request) {

        UpdateAdditionalInfoCommand command = UpdateAdditionalInfoCommand.fromRequest(id,request );
        UpdateAdditionalInfoMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteAdditionalMessage> deleteServices(@PathVariable("id") UUID id) {

        DeleteAdditionalInfoCommand command = new DeleteAdditionalInfoCommand(id);
        DeleteAdditionalMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}