package com.kynsof.treatments.application.query.examOrder.getexanorderbyidexternalconsultation;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindExamOrderByIdExternalConsultationQuery implements IQuery {

    private final UUID id;

    public FindExamOrderByIdExternalConsultationQuery(UUID id) {
        this.id = id;
    }

}
