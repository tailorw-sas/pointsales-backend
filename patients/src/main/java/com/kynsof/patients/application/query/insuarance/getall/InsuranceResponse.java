package com.kynsof.patients.application.query.insuarance.getall;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsof.patients.domain.dto.InsuranceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InsuranceResponse implements IResponse {
    private UUID id;
    private String description;
    private String name;

    public InsuranceResponse(InsuranceDto insuranceDto) {
        this.id = insuranceDto.getId();
        this.description = insuranceDto.getDescription();
        this.name = insuranceDto.getName();
    }

}