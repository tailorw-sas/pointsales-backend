package com.kynsof.patients.application.command.create;

import com.kynsof.patients.domain.Patients;
import com.kynsof.patients.infrastructure.service.PatientsServiceImpl;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePatientsCommandHandler {

    @Autowired
    private PatientsServiceImpl serviceImpl;
    
    public void handle(PatientsRequest command) {

        this.serviceImpl.createService(new Patients(UUID.randomUUID(), command.getName(), command.getLastName(), command.getIdentification(), command.getGender()));

    }
}
