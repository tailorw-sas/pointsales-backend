package com.kynsof.treatments.application.command.externalConsultation.createAll;

import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamAllRequest {
    private String name;
    private String description;
    private MedicalExamCategory type;
    private Double price;
    private String code;
}
