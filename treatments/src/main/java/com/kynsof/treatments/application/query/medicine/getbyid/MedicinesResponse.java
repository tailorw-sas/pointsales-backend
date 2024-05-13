package com.kynsof.treatments.application.query.medicine.getbyid;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class MedicinesResponse implements IResponse {

    private UUID id;
    private String name;

    public MedicinesResponse(MedicinesDto medicinesDto) {
        this.id = medicinesDto.getId();
        this.name = medicinesDto.getName();
    }

}
