package com.kynsof.treatments.application.command.examOrder.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExamOrderRequest {

    private String reason;
    private UUID patientId;
    private List<ExamRequest> exams;
}
