package com.kynsof.treatments.application.command.exam.update;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExamRequest {
    private String name;
    private String description;
    private MedicalExamCategory type;
    private String result;
    private Double price;
}
