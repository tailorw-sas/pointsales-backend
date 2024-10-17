package com.kynsof.treatments.application.query.paymentAllergy.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PaymentAllergyResponse implements IResponse {

    private UUID id;
    private PatientDto patient;
    private Cie10Dto cie10;
    private String severity;
    private String reaction;
    private LocalDateTime createdAt;

    public PaymentAllergyResponse(PatientAllergyDto dto) {
        this.id = dto.getId();
        this.patient = dto.getPatient();
        this.cie10 = dto.getCie10();
        this.severity = dto.getSeverity();
        this.reaction = dto.getReaction();
        this.createdAt = dto.getCreatedAt();
    }

}
