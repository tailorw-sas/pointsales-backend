package com.kynsof.shift.application.command.tunerSpecialties.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final LocalDateTime shiftDateTime;
    private final LocalTime consultationTime;
    private final String business;

    public CreateTurnerSpecialtiesCommand(String medicalHistory, String patient, String identification,
                                          String resource, String service, String status,
                                          LocalDateTime shiftDateTime, LocalTime consultationTime, String business) {
        this.shiftDateTime = shiftDateTime;
        this.consultationTime = consultationTime;
        this.business = business;
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
                request.getStatus(),
                request.getShiftDateTime()
                , request.getConsultationTime(),request.getBusiness() );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTurnerSpecialtiesMessage(id);
    }
}
