package com.kynsof.patients.application.query.currentMedication.getById;

import com.kynsof.patients.application.query.currentMedication.getall.CurrentMedicationResponse;
import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import com.kynsof.patients.domain.service.ICurrentMedicationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindByIdCurrentMedicationQueryHandler implements IQueryHandler<FindByIdCurrentMedicationIQuery, CurrentMedicationResponse>  {

    private final ICurrentMedicationService serviceImpl;

    public FindByIdCurrentMedicationQueryHandler(ICurrentMedicationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public CurrentMedicationResponse handle(FindByIdCurrentMedicationIQuery query) {
        CurrentMerdicationEntityDto currentMerdicationEntityDto = serviceImpl.findById(query.getId());

        return new CurrentMedicationResponse(currentMerdicationEntityDto);
    }
}
