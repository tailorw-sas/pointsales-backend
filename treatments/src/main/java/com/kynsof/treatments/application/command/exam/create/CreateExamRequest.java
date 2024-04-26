package com.kynsof.treatments.application.command.exam.create;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateExamRequest {
    private String name;
    private String description;
    private MedicalExamCategory type;
    private String result;
    private Double price;
}
