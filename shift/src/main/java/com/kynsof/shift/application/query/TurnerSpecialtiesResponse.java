package com.kynsof.shift.application.query;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.shift.application.query.service.ServicesResponse;
import com.kynsof.shift.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.shift.domain.dto.enumType.ETurnerSpecialtiesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TurnerSpecialtiesResponse implements IResponse {

    private UUID id;
    private String medicalHistory;
    private String patient;
    private String identification;
    private ResourceResponse resource;//Doctor
    private ServicesResponse service;//Specialties
    private ETurnerSpecialtiesStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private  LocalDateTime shiftDateTime;
    private  LocalTime consultationTime;

    public TurnerSpecialtiesResponse(TurnerSpecialtiesDto dto) {
        this.id = dto.getId();
        this.medicalHistory = dto.getMedicalHistory();
        this.patient = dto.getPatient();
        this.identification = dto.getIdentification();
        this.resource = dto.getResource() != null ? new ResourceResponse(dto.getResource()) : null;
        this.service = dto.getService() != null ? new ServicesResponse(dto.getService()) : null;
        this.status = dto.getStatus();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
        this.shiftDateTime = dto.getShiftDateTime();
        this.consultationTime = dto.getConsultationTime();

    }

}
