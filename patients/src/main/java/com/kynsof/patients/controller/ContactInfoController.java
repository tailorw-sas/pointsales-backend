package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoCommand;
import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoMessage;
import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoRequest;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoCommand;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoMessage;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoRequest;
import com.kynsof.patients.application.command.patients.delete.DeletePatientsCommand;
import com.kynsof.patients.application.command.patients.delete.PatientDeleteMessage;
import com.kynsof.patients.application.query.contactInfo.getById.FindByIdContactInfoQuery;
import com.kynsof.patients.application.query.contactInfo.getall.ContactInfoResponse;
import com.kynsof.patients.application.query.contactInfo.getall.GetAllContactInfoQuery;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/contact-info")
public class ContactInfoController {

    private final IMediator mediator;

    public ContactInfoController(IMediator mediator){

        this.mediator = mediator;
    }

    @PostMapping("")
    public ResponseEntity<CreateContactInfoMessage> create(@RequestBody CreateContactInfoRequest request)  {
        CreateContactInfoCommand createCommand = CreateContactInfoCommand.fromRequest(request);
        CreateContactInfoMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idPatients,
                                                    @RequestParam(defaultValue = "") String email,
                                                    @RequestParam(defaultValue = "") String phone)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        GetAllContactInfoQuery query = new GetAllContactInfoQuery(pageable, idPatients, email,phone);
        PaginatedResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactInfoResponse> getById(@PathVariable UUID id) {

        FindByIdContactInfoQuery query = new FindByIdContactInfoQuery(id);
        ContactInfoResponse response = mediator.send(query);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateContactInfoMessage> update(@PathVariable UUID id, @RequestBody UpdateContactInfoRequest request) {

        UpdateContactInfoCommand command = UpdateContactInfoCommand.fromRequest(id,request );
        UpdateContactInfoMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDeleteMessage> deleteServices(@PathVariable("id") UUID id) {

        DeletePatientsCommand command = new DeletePatientsCommand(id);
        PatientDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
