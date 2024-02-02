package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.contactInfo.create.ContactInfoContactMessage;
import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoCommand;
import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoRequest;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoCommand;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoMessage;
import com.kynsof.patients.application.command.contactInfo.update.UpdateContactInfoRequest;
import com.kynsof.patients.application.command.patients.create.CreatePatientMessage;
import com.kynsof.patients.application.command.patients.create.CreatePatientsCommand;
import com.kynsof.patients.application.command.patients.create.CreatePatientsRequest;
import com.kynsof.patients.application.command.patients.delete.PatientDeleteMessage;
import com.kynsof.patients.application.command.patients.delete.PatientsDeleteCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientMessage;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsCommand;
import com.kynsof.patients.application.command.patients.update.UpdatePatientsRequest;
import com.kynsof.patients.application.query.getById.FindPatientsByIdQuery;
import com.kynsof.patients.application.query.getall.FindPatientsWithFilterQuery;
import com.kynsof.patients.application.query.getall.PaginatedResponse;
import com.kynsof.patients.application.query.getall.PatientsResponse;
import com.kynsof.patients.domain.bus.IMediator;
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
    public ResponseEntity<ContactInfoContactMessage> create(@RequestBody CreateContactInfoRequest request)  {
        CreateContactInfoCommand createCommand = CreateContactInfoCommand.fromRequest(request);
        ContactInfoContactMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idPatients,
                                                    @RequestParam(defaultValue = "") String identification)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindPatientsWithFilterQuery query = new FindPatientsWithFilterQuery(pageable, idPatients, identification);
        PaginatedResponse data = mediator.send(query);

        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientsResponse> getById(@PathVariable UUID id) {

        FindPatientsByIdQuery query = new FindPatientsByIdQuery(id);
        PatientsResponse response = mediator.send(query);

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

        PatientsDeleteCommand command = new PatientsDeleteCommand(id);
        PatientDeleteMessage response = mediator.send(command);

        return ResponseEntity.ok(response);
    }

}
