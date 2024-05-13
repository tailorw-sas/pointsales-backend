package com.kynsof.treatments.application.command.externalConsultation.createAll;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateExamOrderAllRequest {
    private String reason;
    private List<ExamAllRequest> exams;
}