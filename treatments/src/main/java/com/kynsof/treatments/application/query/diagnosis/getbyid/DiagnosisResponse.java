package com.kynsof.treatments.application.query.diagnosis.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DiagnosisResponse implements IResponse {
    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;

    public DiagnosisResponse(DiagnosisDto diagnosisDto) {
        this.id = diagnosisDto.getId();
        this.icdCode = diagnosisDto.getIcdCode();
        this.description = diagnosisDto.getDescription();
    }

}
