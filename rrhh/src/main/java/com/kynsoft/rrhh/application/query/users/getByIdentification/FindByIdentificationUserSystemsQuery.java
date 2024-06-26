package com.kynsoft.rrhh.application.query.users.getByIdentification;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

@Getter
public class FindByIdentificationUserSystemsQuery implements IQuery {

    private final String identification;

    public FindByIdentificationUserSystemsQuery(String identification) {
        this.identification = identification;
    }

}
