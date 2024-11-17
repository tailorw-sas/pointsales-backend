package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
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
    private MedicalExamCategory type;
}
