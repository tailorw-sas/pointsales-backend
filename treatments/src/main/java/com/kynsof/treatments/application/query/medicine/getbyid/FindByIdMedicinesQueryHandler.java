package com.kynsof.treatments.application.query.medicine.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdMedicinesQueryHandler implements IQueryHandler<FindByIdMedicinesQuery, MedicinesResponse> {

    private final IMedicinesService serviceImpl;

    public FindByIdMedicinesQueryHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public MedicinesResponse handle(FindByIdMedicinesQuery query) {
        MedicinesDto object = serviceImpl.findById(query.getId());

        return new MedicinesResponse(object);
    }
}
