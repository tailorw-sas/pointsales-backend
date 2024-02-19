package com.kynsof.treatments.application.command.examOrder.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamRequest {
    private String name;
    private String description;
    private String type;
    private Double price;
}
