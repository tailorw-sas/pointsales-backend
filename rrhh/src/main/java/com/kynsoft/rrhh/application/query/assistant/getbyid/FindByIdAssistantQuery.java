package com.kynsoft.rrhh.application.query.assistant.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByIdAssistantQuery implements IQuery {

    private final UUID id;

    public FindByIdAssistantQuery(UUID id) {
        this.id = id;
    }

}
