package com.kynsof.treatments.application.command.examOrder.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateExamOrderRequest {

    private String reason;
    private UUID patient;
    private List<UpdateExamRequest> exams;
}
