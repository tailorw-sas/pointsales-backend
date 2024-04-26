package com.kynsof.treatments.application.query.medicine.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchMedicinesQueryHandler implements IQueryHandler<GetSearchMedicinesQuery, PaginatedResponse>{

    private final IMedicinesService serviceImpl;

    public GetSearchMedicinesQueryHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public PaginatedResponse handle(GetSearchMedicinesQuery query) {

        return this.serviceImpl.search(query.getPageable(),query.getFilter());
    }
}
