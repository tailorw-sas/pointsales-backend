package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQuery;
import com.kynsof.share.core.domain.request.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetEligibleVaccinesQuery implements IQuery {
    private UUID patientId;
    private Pageable pageable;
    private List<FilterCriteria> filter;
}
