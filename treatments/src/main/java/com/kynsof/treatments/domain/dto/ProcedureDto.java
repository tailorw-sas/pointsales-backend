package com.kynsof.treatments.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ProcedureDto {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String type;
    private Double price;
}
