package com.kynsof.treatments.application.query.externalConsultation.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdExternalConsultationQuery implements IQuery {

    private final UUID id;

    public FindByIdExternalConsultationQuery(UUID id) {
        this.id = id;
    }

}
