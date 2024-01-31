package com.kynsof.patients.infrastructure.port;

import com.kynsof.patients.application.command.create.CreatePatientsCommandHandler;
import com.kynsof.patients.application.command.create.PatientsRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private CreatePatientsCommandHandler createPatientsCommandHandler;

    @PostMapping("")
    public ResponseEntity<UUID> create(@RequestBody PatientsRequest request) throws Exception {
        this.createPatientsCommandHandler.handle(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
