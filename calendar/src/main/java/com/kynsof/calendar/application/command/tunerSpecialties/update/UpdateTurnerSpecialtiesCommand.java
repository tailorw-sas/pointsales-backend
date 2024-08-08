package com.kynsof.calendar.application.command.tunerSpecialties.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTurnerSpecialtiesCommand implements ICommand {

    private String id;
    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;//Doctor
    private String service;//Specialties
    private String status;

    public UpdateTurnerSpecialtiesCommand(String id, String medicalHistory, String patient, String identification, String resource, String service, String status) {
        this.id = id;
        this.medicalHistory = medicalHistory;
        this.patient = patient;
        this.identification = identification;
        this.resource = resource;
        this.service = service;
        this.status = status;
    }

    public static UpdateTurnerSpecialtiesCommand fromRequest(UpdateTurnerSpecialtiesRequest request, String id) {
        return new UpdateTurnerSpecialtiesCommand(id,
                                                  request.getMedicalHistory(), 
                                                  request.getPatient(),
                                                  request.getIdentification(),
                                                  request.getResource(),
                                                  request.getService(),
                                                  request.getStatus()
                                                );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateTurnerSpecialtiesMessage(id);
    }
}
