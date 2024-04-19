package com.kynsof.treatments.application.command.procedure.update;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateProcedureRequest {
    private String code;
    private String name;
    private String description;
    private MedicalExamCategory type;
    private Double price;
}
