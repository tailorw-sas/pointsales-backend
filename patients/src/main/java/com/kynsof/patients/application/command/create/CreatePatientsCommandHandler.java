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
    
    public UUID handle(PatientsRequest command) {
        Patients patients = new Patients();
        patients.setId(UUID.randomUUID());
        patients.setName(command.getName());
        patients.setLastName(command.getLastName());
        patients.setIdentification(command.getIdentification());
        patients.setGender(command.getGender());

        this.serviceImpl.createService(patients);
        
        return patients.getId();
    }
}
