package com.kynsof.treatments.application.query.treatment.getdiagnosisbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindTreatmentByIdExternalConsultationQuery implements IQuery {

    private final UUID id;
    private final Pageable pageable;

    public FindTreatmentByIdExternalConsultationQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
