package com.kynsof.treatments.application.query.consultHistory;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.ConsultHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ConsultHistoryResponse implements IResponse {
    private UUID id;
    private String identification;
    private String observations;
    private String motive;
    private String physicalExamination;
    private int consultId;
    private String speciality;
    private String doctor;
    private Date consultDate;

    public ConsultHistoryResponse(ConsultHistoryDto object) {
        this.id = object.getId();
        this.identification = object.getIdentification();
        this.observations = object.getObservations();
        this.motive = object.getMotive();
        this.physicalExamination = object.getPhysicalExamination();
        this.consultId = object.getConsultId();
        this.speciality = object.getSpeciality();
        this.doctor = object.getDoctor();
        this.consultDate = object.getConsultDate();

    }

}