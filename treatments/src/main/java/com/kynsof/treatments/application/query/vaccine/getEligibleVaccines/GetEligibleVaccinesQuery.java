package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GetEligibleVaccinesQuery implements IQuery {

    private LocalDate birthDate;

}
