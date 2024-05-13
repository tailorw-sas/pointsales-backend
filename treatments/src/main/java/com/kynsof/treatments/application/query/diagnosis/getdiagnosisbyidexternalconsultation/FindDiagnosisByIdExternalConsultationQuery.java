package com.kynsof.treatments.application.query.diagnosis.getdiagnosisbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindDiagnosisByIdExternalConsultationQuery implements IQuery {

    private final UUID id;
    private final Pageable pageable;

    public FindDiagnosisByIdExternalConsultationQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
