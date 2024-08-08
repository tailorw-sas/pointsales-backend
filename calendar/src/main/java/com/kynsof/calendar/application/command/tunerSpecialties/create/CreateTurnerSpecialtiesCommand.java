package com.kynsof.calendar.application.command.tunerSpecialties.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTurnerSpecialtiesCommand implements ICommand {

    private UUID id;
    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;//Doctor
    private String service;//Specialties
    private String status;

    public CreateTurnerSpecialtiesCommand(String medicalHistory, String patient, String identification, String resource, String service, String status) {
        this.id = UUID.randomUUID();
        this.medicalHistory = medicalHistory;
        this.patient = patient;
        this.identification = identification;
        this.resource = resource;
        this.service = service;
        this.status = status;
    }

    public static CreateTurnerSpecialtiesCommand fromRequest(CreateTurnerSpecialtiesRequest request) {
        return new CreateTurnerSpecialtiesCommand(request.getMedicalHistory(), 
                                                  request.getPatient(),
                                                  request.getIdentification(),
                                                  request.getResource(),
                                                  request.getService(),
                                                  request.getStatus()
                                                );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTurnerSpecialtiesMessage(id);
    }
}
