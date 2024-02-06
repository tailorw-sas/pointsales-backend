package com.kynsof.treatments.application.query.cie10.getAll;


import com.kynsof.treatments.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Cie10Response implements IResponse {
    private UUID id;

    private String code;
    private String name;

    public Cie10Response(Cie10Dto cie10Dto) {
        this.id = cie10Dto.getId();
        this.code = cie10Dto.getCode();
        this.name = cie10Dto.getName();
    }

}