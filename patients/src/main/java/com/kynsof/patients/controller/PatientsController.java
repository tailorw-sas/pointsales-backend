package com.kynsof.patients.controller;

import com.kynsof.patients.application.command.create.CreatePatientMessage;
import com.kynsof.patients.application.command.create.CreatePatientsCommand;
import com.kynsof.patients.application.command.create.CreatePatientsCommandHandler;
import com.kynsof.patients.application.command.create.PatientsRequest;
import com.kynsof.patients.application.query.getall.FindPatientsWithFilterQuery;
import com.kynsof.patients.application.query.getall.GetAllPatientsCommandHandler;
import com.kynsof.patients.application.query.getall.PaginatedResponse;
import java.util.UUID;

import com.kynsof.patients.domain.bus.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private CreatePatientsCommandHandler createPatientsCommandHandler;
    
    @Autowired
    private GetAllPatientsCommandHandler getAllPatientsCommandHandler;

    private final IMediator mediator;

    public PatientsController(IMediator mediator){

        this.mediator = mediator;
    }
    @PostMapping("")
    public ResponseEntity<UUID> create(@RequestBody PatientsRequest request)  {
        CreatePatientsCommand createCommand = CreatePatientsCommand.fromRequest(request);
        CreatePatientMessage response = mediator.send(createCommand);

       // return ResponseEntity.ok(new ApiResponse2xx<CreatePatientMessage>(response, HttpStatus.CREATED));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<PaginatedResponse> getAll(@RequestParam(defaultValue = "20") Integer pageSize,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") UUID idPatients,
                                                    @RequestParam(defaultValue = "") String identification)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        FindPatientsWithFilterQuery query = new FindPatientsWithFilterQuery(pageable, idPatients, identification);
        PaginatedResponse data = getAllPatientsCommandHandler.handle(query);

        return ResponseEntity.ok(data);
    }

}
