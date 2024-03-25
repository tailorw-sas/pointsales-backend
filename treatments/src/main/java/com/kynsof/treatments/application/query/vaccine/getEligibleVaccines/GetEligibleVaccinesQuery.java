package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetEligibleVaccinesQuery implements IQuery {

    private double age;

}
