package com.kynsof.calendar.application.query.tunerSpecialties.getById;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindTurnerSpecialtiesByIdQuery  implements IQuery {

    private UUID id;

    public FindTurnerSpecialtiesByIdQuery(UUID id) {
        this.id = id;
    }

}
