package com.kynsof.treatments.application.query.diagnosis.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.DoctorDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DiagnosisExternalConsultationResponse implements IResponse {

    private UUID id;

    private PatientDto patient;
    private DoctorDto doctor;

    public DiagnosisExternalConsultationResponse(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.patient = dto.getPatient();
        this.doctor = dto.getDoctor();
    }

}
