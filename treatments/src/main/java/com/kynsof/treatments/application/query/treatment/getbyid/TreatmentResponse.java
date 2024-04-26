package com.kynsof.treatments.application.query.treatment.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentResponse implements IResponse {
    private UUID id;
    private String description;
    private String medication;
    private String quantity;
    private String duration;

    public TreatmentResponse(TreatmentDto treatmentDto) {
        this.id = treatmentDto.getId();
        this.description = treatmentDto.getDescription();
        this.medication = treatmentDto.getMedication();
        this.quantity = treatmentDto.getQuantity();
        this.duration = treatmentDto.getDuration();
    }

}
