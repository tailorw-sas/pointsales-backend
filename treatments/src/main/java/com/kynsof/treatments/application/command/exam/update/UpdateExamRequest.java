package com.kynsof.treatments.application.command.exam.update;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateExamRequest {
    private String name;
    private String description;
    private MedicalExamCategory type;
    private String result;
    private Double price;
}
