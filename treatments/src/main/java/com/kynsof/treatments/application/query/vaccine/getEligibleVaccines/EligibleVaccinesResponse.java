package com.kynsof.treatments.application.query.vaccine.getEligibleVaccines;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EligibleVaccinesResponse implements IResponse {
    private List<VaccineResponse> vaccineResponses;

}