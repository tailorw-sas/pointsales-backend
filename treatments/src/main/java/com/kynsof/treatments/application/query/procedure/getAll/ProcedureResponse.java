package com.kynsof.treatments.application.query.procedure.getAll;


import com.kynsof.treatments.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ProcedureResponse implements IResponse {
    private UUID id;

    private String code;
    private String name;
    private String description;
    private String type;
    private Double price;

    public ProcedureResponse(ProcedureDto procedureDto) {
        this.id = procedureDto.getId();
        this.code = procedureDto.getCode();
        this.name = procedureDto.getName();
        this.description = procedureDto.getDescription();
        this.type = procedureDto.getType();
        this.price = procedureDto.getPrice();
    }
}