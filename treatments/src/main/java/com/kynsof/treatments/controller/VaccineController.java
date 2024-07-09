package com.kynsof.treatments.controller;

import com.kynsof.share.core.domain.request.PageableUtil;
import com.kynsof.share.core.domain.request.SearchRequest;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.treatments.application.command.vaccine.create.CreateVaccineCommand;
import com.kynsof.treatments.application.command.vaccine.create.CreateVaccineMessage;
import com.kynsof.treatments.application.command.vaccine.create.CreateVaccineRequest;
import com.kynsof.treatments.application.command.vaccine.delete.VaccineDeleteCommand;
import com.kynsof.treatments.application.command.vaccine.delete.VaccineDeleteMessage;
import com.kynsof.treatments.application.command.vaccine.update.UpdateVaccineCommand;
import com.kynsof.treatments.application.command.vaccine.update.UpdateVaccineMessage;
import com.kynsof.treatments.application.command.vaccine.update.UpdateVaccineRequest;
import com.kynsof.treatments.application.query.vaccine.getById.FindByIdVaccineQuery;
import com.kynsof.treatments.application.query.vaccine.getEligibleVaccines.GetEligibleVaccinesQuery;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.application.query.vaccine.search.GetSearchVaccineQuery;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    private final IMediator mediator;
    private final ResourceLoader resourceLoader;

    public VaccineController(IMediator mediator, ResourceLoader resourceLoader){

        this.mediator = mediator;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateVaccineRequest request)  {
        CreateVaccineCommand createCommand = CreateVaccineCommand.fromRequest(request);
        CreateVaccineMessage response = mediator.send(createCommand);

        return ResponseEntity.ok(response);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<VaccineResponse> getById(@PathVariable UUID id) {

        FindByIdVaccineQuery query = new FindByIdVaccineQuery(id);
        VaccineResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/eligible/{patientId}")
    public ResponseEntity<?> getEligibleVaccines( @PathVariable  UUID patientId,@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);
        GetEligibleVaccinesQuery query = new GetEligibleVaccinesQuery(patientId, pageable, request.getFilter());
        PaginatedResponse response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateVaccineRequest request) {
        UpdateVaccineCommand command = UpdateVaccineCommand.fromRequest(id,request );
        UpdateVaccineMessage response = mediator.send(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PaginatedResponse> search(@RequestBody SearchRequest request)
    {
        Pageable pageable = PageableUtil.createPageable(request);
        GetSearchVaccineQuery query = new GetSearchVaccineQuery(pageable, request.getFilter(),request.getQuery());
        PaginatedResponse data = mediator.send(query);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        VaccineDeleteCommand query = new VaccineDeleteCommand(id);
        VaccineDeleteMessage response = mediator.send(query);
        return ResponseEntity.ok(response);
    }

//    @PatchMapping(path = "/{id}")
//    private  ResponseEntity<?> generateCertificate(){
//        Resource users = resourceLoader.getResource("classpath:templates/hello.jrxml");
//        InputStream inputStream = users.getInputStream();
//
//    }

    @GetMapping("/certificate/{patientId}")
    public ResponseEntity<byte[]> getPDF(@PathVariable UUID patientId) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/" + "certtificate.pdf");
            if (resource.exists()) {
                InputStream inputStream = resource.getInputStream();
                byte[] data = StreamUtils.copyToByteArray(inputStream);
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(data);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}