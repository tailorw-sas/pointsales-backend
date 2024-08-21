package com.kynsof.shift.application.command.tunerSpecialties.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateTurnerSpecialtiesCommand implements ICommand {

    private String id;
    private String medicalHistory;
    private String patient;
    private String identification;
    private String resource;
    private String service;
    private String status;
    private final LocalDateTime shiftDateTime;
    private final LocalTime consultationTime;
    private final String business;

    public UpdateTurnerSpecialtiesCommand(String id, String medicalHistory, String patient, String identification,
                                          String resource, String service, String status, LocalDateTime shiftDateTime,
                                          LocalTime consultationTime, String business) {
        this.id = id;
        this.medicalHistory = medicalHistory;
        this.patient = patient;
        this.identification = identification;
        this.resource = resource;
        this.service = service;
        this.status = status;
        this.shiftDateTime = shiftDateTime;
        this.consultationTime = consultationTime;
        this.business = business;
    }

    public static UpdateTurnerSpecialtiesCommand fromRequest(UpdateTurnerSpecialtiesRequest request, String id) {
        return new UpdateTurnerSpecialtiesCommand(id,
                                                  request.getMedicalHistory(), 
                                                  request.getPatient(),
                                                  request.getIdentification(),
                                                  request.getResource(),
                                                  request.getService(),
                                                  request.getStatus(),
                request.getShiftDateTime()
                , request.getConsultationTime(),
                request.getBusiness());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateTurnerSpecialtiesMessage(id);
    }
}
